package com.lostandfoundapp.dao.lostitemoperations;

import com.lostandfoundapp.dao.lostitem.LostItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LostItemDAO {
     ArrayList<LostItem> getListOfLostItem() throws ClassNotFoundException, SQLException;
     void deleteListOfLostItem() throws SQLException;
     void insertListOfLostItem(ArrayList<LostItem> listOfItems) throws ClassNotFoundException, SQLException;

}
