package com.github.Hanselmito.Model.Conection;

import com.github.Hanselmito.Utils.XMLmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionMariaDB {
    private final static String FILE="Conection.xml";
    private static ConectionMariaDB _instance;
    private static Connection conn;

    private ConectionMariaDB(){
        ConectionPropeties propeties = (ConectionPropeties) XMLmanager.readXML(new ConectionPropeties(),FILE);

        try {
            conn = DriverManager.getConnection
                    (propeties.getURL(),propeties.getUser(), propeties.getPassword()
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
