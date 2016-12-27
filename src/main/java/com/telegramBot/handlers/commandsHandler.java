package com.telegramBot.handlers;

import com.telegramBot.commands.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by Patricio Tula on 27-Dec-16.
 **/
public class commandsHandler extends TelegramLongPollingCommandBot {

    public static final String LOGTAG="COMMANDSHANDLER";

    public commandsHandler(){
        register(new iniciarEventoCmd());
        register(new lugarCmd());
        register(new horaCmd());
        register(new voyCmd());
        register(new noVoyCmd());
        register(new imprimirEventoCmd());
        register(new finalizarEventoCmd());

        //En caso de que no sea correcto el mensaje
        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("El comando '" + message.getText() + "' es inexistente.");
            try {
                absSender.sendMessage(commandUnknownMessage);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
            helpCmd.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
        });
    }


    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId());
                echoMessage.setText("Hey heres your message:\n" + message.getText());

                try {
                    sendMessage(echoMessage);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "DesvirtueEventosBot";
    }

    @Override
    public String getBotToken() {
        return "247274112:AAGz_Vw7cl2C2MgDEgZR1ksMtpLdXzJLrL8";
    }

}
