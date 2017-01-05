package com.telegramBot.commands;

import com.telegramBot.database.conectionDB;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *   Descripcion:
 *       Imprime la lista de invitados del evento.
 *   Argumentos:
 *       No recibe argumentos este comando.
 **/

public class invitadosCmd extends BotCommand {
    private static final String LOGTAG="INVITADOSCMD";

    public invitadosCmd(){
        super("invitados","Imprime la lista de invitados del evento. Ejemplo de ejecucion: /invitados .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        List<String> invitadosEvento = new ArrayList<>();
        boolean tablaExiste=true;
        try {
            final conectionDB connection = new conectionDB();
            final PreparedStatement preparedStatement = connection.getPreparedStatement("SELECT * FROM TABLA_INVITADOS");
            final ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                invitadosEvento.add(result.getString("NOMBRE"));
            }
            connection.closeConexion();
        } catch (SQLException e) {
            tablaExiste=false;
        }

        StringBuilder messageBuilder =  new StringBuilder();

        if(tablaExiste==true){
            messageBuilder.append("<b>Lista de invitados:</b>\n");
            for(String invitado : invitadosEvento)
                messageBuilder.append(invitado + "\n");
        }else{
            messageBuilder.append("<b>No hay Lista de invitados para imprimir</b>\n");
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());

        try{
            absSender.sendMessage(answer);
        }catch(TelegramApiException e){
            BotLogger.error(LOGTAG, e);
        }

    }
}
