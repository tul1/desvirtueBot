package com.telegramBot.commands;

import com.telegramBot.database.conectionDB;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.sql.SQLException;

/**
*   Descripcion:
*       Inicia un evento. Crea tabla de coordenadas del evento y la de invitados.
*   Argumentos:
*       Recibe 1 solo argumento el nombre del evento. Por defecto le agrega el nombre "Evento Desvirtue!" si no se pasa ningun argumento.
**/

public class iniciarEventoCmd extends BotCommand {
    private static final String LOGTAG = "INICIAREVENTOCMD";

    public iniciarEventoCmd(){
        super("iniciarEvento", "Con este comando podras iniciar un evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String nombreEvento= new String();
        if(strings.length==0){
            nombreEvento="Evento Desvirtue!";
        }else{
            for(int i=0; i<strings.length; i++)
                nombreEvento += strings[i]+ ((i<strings.length-1)?" ":"");
        }

        try {
            final conectionDB connection = new conectionDB();

            //creo tabla de evento
            connection.executeQuery("CREATE TABLE IF NOT EXISTS `DESVIRTUEDB`.`TABLA_EVENTO` ( `ID` INT NOT NULL DEFAULT 1, `NOMBRE` VARCHAR(50) DEFAULT '' ,`FECHA` VARCHAR(20) DEFAULT '', `HORA` VARCHAR(20) DEFAULT '', `LUGAR` VARCHAR(20) DEFAULT '', PRIMARY KEY (`ID`)) ENGINE = InnoDB");
            connection.executeQuery("INSERT INTO `TABLA_EVENTO` (`NOMBRE`) VALUES ('"+nombreEvento+"') ON DUPLICATE KEY UPDATE ID = 1");
            //creo tabla de invitados
            connection.executeQuery("CREATE TABLE IF NOT EXISTS `DESVIRTUEDB`.`TABLA_INVITADOS` ( `ID` INT NOT NULL AUTO_INCREMENT, `NOMBRE` VARCHAR(50) NOT NULL, PRIMARY KEY (`ID`)) ENGINE = InnoDB");
            //creo las tablas bup
            connection.executeQuery("CREATE TABLE IF NOT EXISTS `DESVIRTUEDB`.`TABLA_EVENTO_BUP` ( `ID` INT NOT NULL DEFAULT 1, `NOMBRE` VARCHAR(50) DEFAULT '' ,`FECHA` VARCHAR(20) DEFAULT '', `HORA` VARCHAR(20) DEFAULT '', `LUGAR` VARCHAR(20) DEFAULT '', PRIMARY KEY (`ID`)) ENGINE = InnoDB");
            connection.executeQuery("INSERT INTO `TABLA_EVENTO_BUP` (`NOMBRE`) VALUES ('"+nombreEvento+"') ON DUPLICATE KEY UPDATE ID = 1");
            connection.executeQuery("CREATE TABLE IF NOT EXISTS `DESVIRTUEDB`.`TABLA_INVITADOS_BUP` ( `ID` INT NOT NULL AUTO_INCREMENT, `NOMBRE` VARCHAR(50) NOT NULL, PRIMARY KEY (`ID`)) ENGINE = InnoDB");

            connection.closeConexion();
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append(nombreEvento+" <b>iniciado</b> \n");

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());

        try{
            absSender.sendMessage(answer);
        }catch(TelegramApiException e){
            BotLogger.error(LOGTAG, e);
        }
    }

}
