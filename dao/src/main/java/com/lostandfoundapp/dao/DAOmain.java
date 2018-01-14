package com.lostandfoundapp.dao;

import com.lostandfoundapp.dao.lostitem.LostItemDAO;
import com.lostandfoundapp.dao.lostitemoperations.OperationsWithDAO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class DAOmain implements OperationsWithDAO {

    private String deleteAndCreateSchemaSQL = "DROP SCHEMA  [ IF EXISTS ] LOST_FOUND_APP; \n" +
            "\n" +
            "CREATE SCHEMA  LOST_FOUND_APP ;\n" +
            "\n" +
            "CREATE TABLE LOST_FOUND_APP.LOST_ITEM  (\n" +
            "  NO int(11) NOT NULL,\n" +
            "  NAME_OF_ITEM varchar(45) DEFAULT NULL,\n" +
            "  CITY_OF_FOUND varchar(45) DEFAULT NULL,\n" +
            "  PLACE_OF_FOUND varchar(45) DEFAULT NULL,\n" +
            "  COMMENT varchar(45) DEFAULT NULL,\n" +
            "  ITEM_SOURCE_ID varchar(45) DEFAULT NULL,\n" +
            "  URL_ADDRESS_OF_SOURCE varchar(45) DEFAULT NULL,\n" +
            "  DATE_OF_FOUND varchar(45) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`NO`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    final String USER = "sa";
    final String PASS = "";


    Connection conn = null;
    Statement stmt = null;

    private void JDBCconnection() throws ClassNotFoundException, SQLException {

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
    public ArrayList<LostItemDAO> getListOfLostItem() throws ClassNotFoundException, SQLException {
        ArrayList<LostItemDAO> listOfLostItemDAO;


        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //STEP 3: Execute a query
        stmt = conn.createStatement();
        //stmt.execute(deleteAndCreateSchemaSQL);





        // STEP 4: Clean-up environment
        stmt.close();
        conn.close();

        listOfLostItemDAO = null;
        return listOfLostItemDAO;
    }

    @Override
    public ArrayList<LostItemDAO> deleteListOfLostItem() {
        return null;
    }

    @Override
    public void insertListOfLostItem(ArrayList<LostItemDAO> lostItemDAOList) throws ClassNotFoundException, SQLException {

        //STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //STEP 3: Execute a query
        stmt = conn.createStatement();
        stmt.execute(deleteAndCreateSchemaSQL);

        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO LOST_FOUND_APP.LOST_ITEM"
                + "(NO, NAME_OF_ITEM, CITY_OF_FOUND, PLACE_OF_FOUND, COMMENT, ITEM_SOURCE_ID, URL_ADDRESS_OF_SOURCE, DATE_OF_FOUND) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        preparedStatement = conn.prepareStatement(insertTableSQL);

        int i = 0;
        for (LostItemDAO lostItemDAO : lostItemDAOList) {

            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, lostItemDAO.getNameOfItem());
            preparedStatement.setString(3, lostItemDAO.getCityOfFound());
            preparedStatement.setString(4, lostItemDAO.getPlaceOfFound());
            preparedStatement.setString(5, lostItemDAO.getComment());
            preparedStatement.setString(6, lostItemDAO.getItemSourceID());
            preparedStatement.setString(7, lostItemDAO.getURLAddressOfSource());
            preparedStatement.setString(8, lostItemDAO.getDateOfFinding());

            preparedStatement.executeUpdate(insertTableSQL);

            ++i;
        }

        //STEP 4: Clean-up environment
        stmt.close();
        preparedStatement.close();
        conn.close();

    }


}
