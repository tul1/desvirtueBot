package com.telegramBot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by Patricio Tula on 27-Dec-16.
 **/
public class lugarCmd extends BotCommand {
    private static final String LOGTAG="LUGARCMD";

    public lugarCmd(){
        super("lugar", "Con este comando podras establecer el lugar del evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("El evento se realizara en ").append("Narnia").append("\n");

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
