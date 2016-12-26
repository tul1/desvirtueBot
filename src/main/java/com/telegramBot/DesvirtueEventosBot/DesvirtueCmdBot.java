package com.telegramBot.DesvirtueEventosBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by Patricio Tula on 12/26/2016.
 **/

public class DesvirtueCmdBot extends TelegramLongPollingCommandBot {
    private static final String LOGTAG = "holas";

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId());
                String msg = message.getText().replace("/desvirtue_bot ","");
                String usrName = message.getFrom().getUserName();
                echoMessage.setText(usrName+" dijo: "+ msg);

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
