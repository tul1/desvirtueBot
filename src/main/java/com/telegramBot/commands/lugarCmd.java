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
*       Settea el lugar donde se realizara la reunion.
*   Argumentos:
*       Recibe 1 solo argumento, una cadena de caracteres que sera el lugar del evento.
**/

public class lugarCmd extends BotCommand {
    private static final String LOGTAG="LUGARCMD";

    public lugarCmd(){
        super("lugar", "Carga el Lugar del evento. Ejemplo de ejecucion: /lugar Pedro Lozano 4075 :-) .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String lugar= new String();
        for(int i=0; i<strings.length; i++)
            lugar += strings[i]+ ((i<strings.length-1)?" ":"");

        String answerStr = new String(lugar+" <b>Lugar cargado</b>\n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("UPDATE `TABLA_EVENTO` SET `LUGAR` = '" + lugar + "' WHERE `TABLA_EVENTO`.`ID` = 1");
            connection.executeQuery("UPDATE `TABLA_EVENTO_BUP` SET `LUGAR` = '" + lugar + "' WHERE `TABLA_EVENTO_BUP`.`ID` = 1");
            connection.closeConexion();
        } catch (SQLException e) {
            answerStr="<b>Debe iniciar un Evento para cargar Lugar.</b>\n";
        }

        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append(answerStr);

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
