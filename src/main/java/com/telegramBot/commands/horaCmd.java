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

/*
    Descripcion:
        Settea la hora a la que se realizara el evento. Carga en la tabla la hora del evento.
*/

public class horaCmd extends BotCommand{
    private static final String LOGTAG = "HORACMD";

    public horaCmd(){
        super("hora","Carga la Hora del evento. Ejemplo de ejecucion: /hora 20:00.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String hora = new String(strings[0]);
        String answerStr = new String(hora+" <b>Hora asignada</b> \n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery( "UPDATE `TABLA_EVENTO` SET `HORA` = '"+hora+"' WHERE `TABLA_EVENTO`.`ID` = 1");
            connection.executeQuery("UPDATE `TABLA_EVENTO_BUP` SET `HORA` = '" + hora + "' WHERE `TABLA_EVENTO_BUP`.`ID` = 1");
            connection.closeConexion();
        } catch (SQLException e) {
//            BotLogger.error(LOGTAG, e);
            answerStr="<b>Debe iniciar un Evento para cargar Hora.</b>\n";
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
