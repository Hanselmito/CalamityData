package com.github.Hanselmito.Model.Conection;

import com.github.Hanselmito.Utils.XMLmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionMariaDB {
    private final static String FILE="connection.xml";
    private static ConectionMariaDB _instance;
    private static Connection conn;

    private ConectionMariaDB(){
        ConnectionProperties properties = (ConnectionProperties) XMLmanager.readXML(new ConnectionProperties(),FILE);

        try {
            conn = DriverManager.getConnection
                    (properties.getURL(),properties.getUser(), properties.getPassword()
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            conn=null;
        }
    }

    public static Connection getConnection(){
        if (_instance==null){
            _instance = new ConectionMariaDB();
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}