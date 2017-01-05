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
 *       Agrega al usuario que invoque a la lista de invitados.
 *   Argumentos:
 *       No recibe argumentos
 **/

public class voyCmd extends BotCommand {
    private static final String LOGTAG = "VOYCMD";

    public voyCmd(){
        super("voy","Da de alta al usuario que lo invoque al Evento. Ejemplo de ejecucion: /voy");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String userName = user.getFirstName() + " " + user.getLastName();
        String answerStr=new String(userName+" <b>cargado</b>\n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("INSERT IGNORE INTO `TABLA_INVITADOS` (`NOMBRE`) VALUES ('"+userName+"')");
            connection.executeQuery("INSERT IGNORE INTO `TABLA_INVITADOS_BUP` (`NOMBRE`) VALUES ('"+userName+"')");
            connection.closeConexion();
        } catch (SQLException e) {
            answerStr="<b>Debe iniciar un Evento para poder participar del mismo.</b>\n";
        }


        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append(answerStr);

        SendMessage answer = new SendMessage();
        answer.enableHtml(true);
        answer.setChatId(chat.getId().toString());
        answer.setText(messageBuilder.toString());

        try{
            absSender.sendMessage(answer);
        }catch(TelegramApiException e){
            BotLogger.error(LOGTAG, e);
        }

    }
}
