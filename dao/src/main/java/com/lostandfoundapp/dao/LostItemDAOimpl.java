package com.lostandfoundapp.dao;

import com.lostandfoundapp.dao.lostitem.LostItem;
import com.lostandfoundapp.dao.lostitemoperations.LostItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LostItemDAOimpl implements LostItemDAO {
/*
    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    final String USER = "sa";
    final String PASS = "";*/

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public ArrayList<LostItem> getListOfLostItem() {

        String getAllRowsSQL = "SELECT * FROM LOST_FOUND_APP.LOST_ITEM";

        jdbcTemplate = new JdbcTemplate(dataSource);
        ArrayList<LostItem> lostItemList = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllRowsSQL);

        for (Map row : rows) {
            //Employee employee = new Employee();
            LostItem lostItem = new LostItem();

          /*  employee.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
            employee.setName((String)row.get("NAME"));
            employee.setAge(Integer.parseInt(String.valueOf(row.get("AGE"))));*/

            lostItem.setItemID(Integer.parseInt(String.valueOf(row.get("LOST_ITEM_ID"))));
            lostItem.setNameOfItem((String)row.get("NAME_OF_ITEM"));
            lostItem.setPlaceOfFound((String)row.get("PLACE_OF_FOUND"));
            lostItem.setCityOfFound((String)row.get("CITY_OF_FOUND"));
            lostItem.setDateOfFinding((String)row.get("DATE_OF_FOUND"));
            lostItem.setURLAddressOfSource((String)row.get("URL_ADDRESS_OF_SOURCE"));
            lostItem.setItemSourceID((String)row.get("ITEM_SOURCE_ID"));
            lostItem.setComment((String)row.get("COMMENT"));

            //employees.add(employee);
            lostItemList.add(lostItem);
        }


        return lostItemList;
    }

    @Override
    public void deleteListOfLostItem(){

        String deleteAllRowsSQL = "DELETE FROM LOST_FOUND_APP.LOST_ITEM;";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(deleteAllRowsSQL, new Object[] { });


        System.out.println("Records are deleted from DBUSER table!");


        /*
        Connection dbConnection = null;
        Statement statement = null;

        String deleteAllRowsSQL = "DELETE FROM LOST_FOUND_APP.LOST_ITEM;";

        try {


            dbConnection = getDBConnection();

            statement = dbConnection.createStatement();

            System.out.println(deleteAllRowsSQL);

            // execute insert SQL stetement
            statement.executeUpdate(deleteAllRowsSQL);

            System.out.println("Records are deleted from DBUSER table!");

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DatabaseException(e);
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new DatabaseException(e);
                }
            }
        }*/
    }

    @Override
    public void insertListOfLostItem(List<LostItem> lostItemList) {

        //deleteListOfLostItem();

//        PreparedStatement preparedStatement = null;
//        Connection dbConnection = null;

        String insertTableSQL = "INSERT INTO LOST_FOUND_APP.LOST_ITEM"
                + "(NAME_OF_ITEM, CITY_OF_FOUND, PLACE_OF_FOUND, COMMENT, ITEM_SOURCE_ID, URL_ADDRESS_OF_SOURCE, DATE_OF_FOUND) VALUES "
                + "(?,?,?,?,?,?,?)";


        jdbcTemplate = new JdbcTemplate(dataSource);

        for (LostItem lostItem : lostItemList) {

            jdbcTemplate.update(insertTableSQL, new Object[] { lostItem.getNameOfItem(), lostItem.getCityOfFound(),
                    lostItem.getPlaceOfFound(), lostItem.getComment(), lostItem.getItemSourceID(),
                    lostItem.getURLAddressOfSource(), lostItem.getDateOfFinding()

            });

        }


                System.out.println("Records are inserted into DBUSER table!");
/*

        try {

            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            System.out.println(insertTableSQL);

            // execute insert SQL stetement
            for (LostItem lostItem : lostItemList) {

                preparedStatement.setString(1, lostItem.getNameOfItem());
                preparedStatement.setString(2, lostItem.getCityOfFound());
                preparedStatement.setString(3, lostItem.getPlaceOfFound());
                preparedStatement.setString(4, lostItem.getComment());
                preparedStatement.setString(5, lostItem.getItemSourceID());
                preparedStatement.setString(6, lostItem.getURLAddressOfSource());
                preparedStatement.setString(7, lostItem.getDateOfFinding());

                preparedStatement.executeUpdate();
                System.out.println("Record is inserted into DBUSER table!");
                //break;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
//                    throw new DatabaseException(e);
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
//                    throw new DatabaseException(e);
                }
            }
        }
*/
    }

/*

    private Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(e);
        }
        try {
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            dbConnection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return dbConnection;
    }
*/

}
