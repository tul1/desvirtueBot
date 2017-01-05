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

/*
*   Descripcion:
*       Imprime las coordenadas del evento.
*   TODO cambiar nombre a "coordenadas"
*   TODO que pasa si la base no fue creada?
*   TODO que pasa si algun elemento es NULL
*
*/

public class imprimirEventoCmd extends BotCommand {
    private static final String LOGTAG="IMPRIMIREVENTOCMD";

    public imprimirEventoCmd(){
        super("imprimirEvento","Este comando imprime los datos del evento, lista de invitados, lugar y hora.");
    }

    public class datoEvento{
        public String label;
        public String value;
        public datoEvento(String l, String v){value=v;label=l;}
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        List<datoEvento> datosEvento = new ArrayList<>();
        try {
            final conectionDB connection = new conectionDB();
            final PreparedStatement preparedStatement = connection.getPreparedStatement("SELECT * FROM TABLA_EVENTO WHERE ID=1");
            final ResultSet result = preparedStatement.executeQuery();
            result.next();
            datosEvento.add(new datoEvento("Fecha", result.getString("FECHA")));
            datosEvento.add(new datoEvento("Hora", result.getString("HORA")));
            datosEvento.add(new datoEvento("Lugar", result.getString("LUGAR")));
            connection.closeConexion();
        } catch (SQLException e) {
            BotLogger.error(LOGTAG, e);
        }

        StringBuilder messageBuilder =  new StringBuilder();
        for(datoEvento dato: datosEvento){
            messageBuilder.append( dato.label + ": ");
            messageBuilder.append("<b>" + dato.value + "</b>\n");
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
