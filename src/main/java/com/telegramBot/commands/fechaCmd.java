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
*   Descripcion:
*       Settea la fecha del evento
*/

public class fechaCmd extends BotCommand {
    private static final String LOGTAG="FECHACMD";

    public fechaCmd(){
        super("fecha", "Carga la Fecha del evento. Ejemplo de ejecucion: /fecha 01/01/17 :-) .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String fecha = strings[0];
        String answerStr = new String(fecha+" <b>Fecha cargada</b>\n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery( "UPDATE `TABLA_EVENTO` SET `FECHA` = '"+fecha+"' WHERE `TABLA_EVENTO`.`ID` = 1");
            connection.executeQuery( "UPDATE `TABLA_EVENTO_BUP` SET `FECHA` = '"+fecha+"' WHERE `TABLA_EVENTO_BUP`.`ID` = 1");
            connection.closeConexion();
        } catch (SQLException e) {
//            BotLogger.error(LOGTAG, e);
            answerStr="<b>Debe iniciar un Evento para cargar Fecha.</b>\n";
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
