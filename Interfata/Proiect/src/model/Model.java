package model;

import java.sql.*;

public class Model {
    protected Connection connection;
    public SQLQueries sqlQueries;
    private String CNP;

    public Model(){
        sqlQueries = new SQLQueries();
    }

    public void getConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "admin");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public Connection getConnectionInstance(){
        return connection;
    }

    public void setCNP(String CNP){
        this.CNP = CNP;
    }

    public String getCNP(){
        return CNP;
    }
}
