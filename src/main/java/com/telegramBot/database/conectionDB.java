package com.telegramBot.database;

import org.telegram.telegrambots.logging.BotLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;




public class conectionDB {
    private static final String LOGTAG="CONECTIONDB";
    private Connection currentConection;

    //TODO PONER TODO ESTO EN UNA CLASE GLOBAL
    private static String urlDB="jdbc:mysql://localhost:3306/DESVIRTUEDB?useUnicode=true&characterEncoding=UTF-8";
    private static String usr="desvirtueBot";
    private static String psw="34117795";

    public conectionDB(){
        this.currentConection=openConexion();
    }

    private Connection openConexion(){
        Connection connection=null;
        try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(urlDB,usr,psw);
        }
        catch ( SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e){
            BotLogger.error(LOGTAG, e);
        }
        return connection;
    }

    public void closeConexion(){
        try{
            this.currentConection.close();
        }catch (SQLException e){
            BotLogger.error(LOGTAG,e);
        }
    }
/*
    public ResultSet runSqlQuery(String query) throws SQLException{
        final Statement statement;
        statement = this.currentConection.createStatement();
        return statement.executeQuery(query);
    }
*/
    public Boolean executeQuery(String query) throws SQLException{
        final Statement statement = this.currentConection.createStatement();
        return statement.execute(query);
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException{
        return this.currentConection.prepareStatement(query);
    }

    public PreparedStatement getPreparedStatement(String query, int flags) throws SQLException{
        return this.currentConection.prepareStatement(query,flags);
    }
/*
    public void initTransaction() throws SQLException{
        this.currentConection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        try {
            this.currentConection.commit();
        } catch (SQLException e) {
            if (this.currentConection != null) {
                this.currentConection.rollback();
            }
        } finally {
            this.currentConection.setAutoCommit(false);
        }
    }
*/
}
