package com.telegramBot.commands;

import com.telegramBot.database.conectionDB;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.sql.SQLException;

/*
    Descripcion:
        Inicia un evento.

*/

public class iniciarEventoCmd extends BotCommand {
    private static final String LOGTAG = "INICIAREVENTOCMD";

    public iniciarEventoCmd(){
        super("iniciarEvento", "Con este comando podras iniciar un evento.");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){

        try {
            final conectionDB connection = new conectionDB();
            connection.executeQuery("CREATE TABLE IF NOT EXISTS `DESVIRTUEDB`.`TABLA_EVENTO` ( `ID` INT NOT NULL DEFAULT 1, `FECHA` VARCHAR(20) DEFAULT '', `HORA` VARCHAR(20) DEFAULT '', `LUGAR` VARCHAR(20) DEFAULT '', PRIMARY KEY (`ID`)) ENGINE = InnoDB");
            connection.executeQuery("INSERT INTO tabla_evento VALUES () ON DUPLICATE KEY UPDATE ID = 1");
            connection.closeConexion();
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        StringBuilder messageBuilder =  new StringBuilder();
        messageBuilder.append("Has iniciado un nuevo evento\n");

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
