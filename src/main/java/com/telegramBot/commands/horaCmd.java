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
        Establece la hora a la que se realizara el evento. Carga en la tabla la hora del evento.
*/

public class horaCmd extends BotCommand{
    private static final String LOGTAG = "HORACMD";
    private static volatile conectionDB connection;

    public horaCmd(){
        super("hora","Con este comando podras establecer la hora del evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){

        String hora= new String(strings[0]);

        connection = new conectionDB();
        try {
            //TODO ESTE QUERRY DEBE IR EN OTRO PAQUETE
            connection.executeQuery( "UPDATE `TABLA_EVENTO` SET `HORA` = '"+hora+"' WHERE `tabla_evento`.`ID` = 1");
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("El evento se realizara a las <b>"+hora+"</b> \n");

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
