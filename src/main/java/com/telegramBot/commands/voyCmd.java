package com.telegramBot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/*
*   Descripcion:
*       Esta funcion agrega un invitado a la lista.
*/

public class voyCmd extends BotCommand {
    private static final String LOGTAG = "VOYCMD";

    public voyCmd(){
        super("voy","Este comando te permitira agregarte a la lista de invitados.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        StringBuilder messageBuilder =  new StringBuilder();
        String userName = user.getFirstName() + " " + user.getLastName();
        messageBuilder.append(userName).append(" bienvenido al evento!\n");

        //conectar a la base de datos y agregar el nombre y apellido del usuario que invoco el comando.

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(messageBuilder.toString());

        try{
            absSender.sendMessage(answer);
        }catch(TelegramApiException e){
            BotLogger.error(LOGTAG, e);
        }

    }
}
