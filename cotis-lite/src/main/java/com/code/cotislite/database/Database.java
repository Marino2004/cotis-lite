package com.code.cotislite.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cotis-lite";
    private static final String DB_user = "root";
    private static final String DB_password ="";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_user,DB_password);
    }
}
/*
    public                      : visibilité en dehors à l'intérieur de la classe
    Attribut statique           : portée de l'attribut qui définit si elle est partagée à toutes les instances dans cette classe c-à-d même valeur pour chacune des instances
    Attrinbut statique finale   : ne peut pas être modidifé après son initialisation mais ne peut pas être accéssible sans instanciation de la classe.
    throws SQLException         : cette méthode indique que lorsqu'elle est déclarée, on doit lever l'exception "throws" */