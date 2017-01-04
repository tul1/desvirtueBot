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

public class fechaCmd extends BotCommand {
    private static final String LOGTAG="FECHACMD";
    private static volatile conectionDB connection;

    public fechaCmd(){
        super("fecha", "Con este comando podras establecer el fecha del evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String fecha = strings[0];
        //se agrega el lugar en la columna necesaria
        connection = new conectionDB();
        try {
            //TODO ESTE QUERRY DEBE IR EN OTRO PAQUETE
            connection.executeQuery( "UPDATE `TABLA_EVENTO` SET `FECHA` = '"+fecha+"' WHERE `tabla_evento`.`ID` = 1");
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        //imprimo el nombre del lugar por la interfaz de telegram
        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("El evento se realizara el <b>"+fecha+"</b>\n");

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
