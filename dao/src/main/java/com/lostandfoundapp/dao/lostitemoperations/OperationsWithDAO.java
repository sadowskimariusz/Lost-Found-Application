package com.lostandfoundapp.dao.lostitemoperations;

import com.lostandfoundapp.dao.lostitem.LostItemDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OperationsWithDAO {
    public ArrayList<LostItemDAO> getListOfLostItem() throws ClassNotFoundException, SQLException;
    public ArrayList<LostItemDAO> deleteListOfLostItem();
    public void insertListOfLostItem(ArrayList<LostItemDAO> listOfItems) throws ClassNotFoundException, SQLException;
}
