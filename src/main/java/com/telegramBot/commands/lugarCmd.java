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
* Descripcion: Estable el lugar donde se realizara la reunion.
*/

public class lugarCmd extends BotCommand {
    private static final String LOGTAG="LUGARCMD";

    public lugarCmd(){
        super("lugar", "Con este comando podras establecer el lugar del evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String lugar= new String();
        for(int i=0; i<strings.length; i++)
            lugar += strings[i]+ ((i<strings.length-1)?" ":"");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery( "UPDATE `TABLA_EVENTO` SET `LUGAR` = '"+lugar+"' WHERE `tabla_evento`.`ID` = 1");
            connection.closeConexion();
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("El evento se realizara en <b>"+lugar+"</b>\n");

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
