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
 *       Recupera un evento eliminado.
 *   Argumentos:
 *       No recibe argumentos
 **/

public class recuperarEventoCmd extends BotCommand {
    private static final String LOGTAG="LUGARCMD";

    public recuperarEventoCmd(){
        super("recuperarEvento", "Recupera Evento anterior si este fue eliminado. Ejemplo de ejecucion: /recuperarevento .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){

        String answerStr = new String("<b>Evento recuperado</b>\n");

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("CREATE TABLE `DESVIRTUEDB`.`TABLA_EVENTO` AS (SELECT * FROM `TABLA_EVENTO_BUP`)");
            connection.executeQuery("CREATE TABLE `DESVIRTUEDB`.`TABLA_INVITADOS` AS (SELECT * FROM `TABLA_INVITADOS_BUP`)");
            connection.closeConexion();
        } catch (SQLException e) {
            answerStr="<b>No pudo recuperarse el evento. Debe finalizar evento para recuperarlo.</b>\n";
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