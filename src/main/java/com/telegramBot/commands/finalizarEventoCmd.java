package com.telegramBot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/*
* Descripcion:
*   Este comando debera destruir los datos de la base unicamente.
* */

public class finalizarEventoCmd  extends BotCommand {

    private static final String LOGTAG="FINALIZAREVENTOCMD";

    public finalizarEventoCmd(){
        super("finalizarEvento","Con este comando finalizaras el evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("Evento finalizado!\n");

        //OPERACIONES CON LA BASE QUE DESTRUYEN LA TABLA CREADA PARA EL EVENTO


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
