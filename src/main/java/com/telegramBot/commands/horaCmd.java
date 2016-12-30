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
        Establece la hora a la que se realizara el evento. Carga en la tabla la hora del evento.
*/

public class horaCmd extends BotCommand{
    private static final String LOGTAG = "HORACMD";

    public horaCmd(){
        super("hora","Con este comando podras establecer la hora del evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){

        //Procesar la cadena que contiene la hora y guardarla en la base



        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("El evento se realizara a las ").append("19:00").append("\n");

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
