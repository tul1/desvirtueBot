package com.telegramBot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

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



        StringBuilder messageBuilder =  new StringBuilder();
        String userName = user.getFirstName() + " " + user.getLastName();
        messageBuilder.append(userName).append(" se da de baja al evento!\n");

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
