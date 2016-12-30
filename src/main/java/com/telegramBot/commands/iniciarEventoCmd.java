package com.telegramBot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/*
    Descripcion:
        Este metodo armara las 2 tablas en la base.
*/

public class iniciarEventoCmd extends BotCommand {

    private static final String LOGTAG = "INICIAREVENTOCMD";

    public iniciarEventoCmd(){
        super("iniciarEvento", "Con este comando podras iniciar un evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("Has iniciado un nuevo evento\n");

        //crear tablas si no existen, si existen el comando no debera hacer nada.

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
