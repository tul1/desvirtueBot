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
*       Elimina el Evento creado.
*   Argumentos:
*       No recibe argumentos este comando.
**/

public class finalizarEventoCmd  extends BotCommand {

    private static final String LOGTAG="FINALIZAREVENTOCMD";

    public finalizarEventoCmd(){
        super("finalizarEvento","Finaliza el evento creado. Ejemplo de ejecicion: /finalizarevento");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        String answerStr = new String();
        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("DROP TABLE `TABLA_EVENTO`");
            connection.executeQuery("DROP TABLE `TABLA_INVITADOS`");
            connection.closeConexion();
            answerStr="<b>Evento finalizado</b>\n";
        } catch (SQLException e) {
//            BotLogger.error(LOGTAG, e);
            answerStr =  "<b>No hay evento para finalizar</b>";
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
