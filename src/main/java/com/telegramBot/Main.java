package com.telegramBot.Main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

public class Main {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
      //      botsApi.registerBot(new com.telegramBot.DesvirtueEventosBot.DesvirtueEventosBot());
            botsApi.registerBot(new com.telegramBot.DesvirtueEventosBot.DesvirtueCmdBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}