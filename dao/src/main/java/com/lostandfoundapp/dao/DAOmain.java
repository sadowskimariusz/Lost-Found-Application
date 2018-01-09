package com.lostandfoundapp.dao;

import com.lostandfoundapp.dao.lostitem.LostItemDAO;
import com.lostandfoundapp.dao.lostitemoperations.OperationsWithDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOmain implements OperationsWithDAO {

    public void JDBCconnection() throws ClassNotFoundException, SQLException {

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";


        Connection conn = null;
        Statement stmt = null;

        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);


        // STEP 4: Clean-up environment
        stmt.close();
        conn.close();
    }


    @Override
    public ArrayList<LostItemDAO> getListOfLostItem() {
        return null;
    }

    @Override
    public ArrayList<LostItemDAO> deleteListOfLostItem() {
        return null;
    }

    @Override
    public ArrayList<LostItemDAO> insertListOfLostItem() {
        return null;
    }


}
