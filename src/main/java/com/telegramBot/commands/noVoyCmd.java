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
 *       Elimina al usuario que invoque de la lista de invitados.
 *   Argumentos:
 *       No recibe argumentos
 **/

public class noVoyCmd extends BotCommand {
    private static final String LOGTAG="NOVOYCMD";

    public noVoyCmd(){
        super("noVoy","Da de baja al usuario que lo invoque al Evento. Ejemplo de ejecucion: /voy .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String userName = user.getFirstName() + " " + user.getLastName();
        String answerStr=new String(userName+" <b>descargado</b>\n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("DELETE FROM `TABLA_INVITADOS` WHERE `NOMBRE` = '"+userName+"'");
            connection.executeQuery("DELETE FROM `TABLA_INVITADOS_BUP` WHERE `NOMBRE` = '"+userName+"'");
            connection.closeConexion();
        } catch (SQLException e) {
            answerStr="<b>Debe cargarse para descargarse.</b>\n";
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
