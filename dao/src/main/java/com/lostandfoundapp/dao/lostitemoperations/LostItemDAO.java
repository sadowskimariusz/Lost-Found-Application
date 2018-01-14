package com.lostandfoundapp.dao.lostitemoperations;

import com.lostandfoundapp.dao.lostitem.LostItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LostItemDAO {
     ArrayList<LostItem> getListOfLostItem() throws ClassNotFoundException, SQLException;
     void deleteListOfLostItem();
     void insertListOfLostItem(List<LostItem> listOfItems);

}
