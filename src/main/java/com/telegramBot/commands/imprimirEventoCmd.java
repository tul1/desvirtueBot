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
*       Este comando debe imprimir el estado actual del evento. Decha, Lugar e invitados.
*/

public class imprimirEventoCmd extends BotCommand {

    private static final String LOGTAG="IMPRIMIREVENTOCMD";

    public imprimirEventoCmd(){
        super("imprimirEvento","Este comando imprime los datos del evento, lista de invitados, lugar y hora.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("Datos del evento!\n");

        //conectarse con la base e imprimir el contenido relevante de las tablas

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
