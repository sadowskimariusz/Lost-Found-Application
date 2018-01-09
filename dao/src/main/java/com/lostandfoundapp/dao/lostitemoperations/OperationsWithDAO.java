package com.lostandfoundapp.dao.lostitemoperations;

import com.lostandfoundapp.dao.lostitem.LostItemDAO;

import java.util.ArrayList;

public interface OperationsWithDAO {
    public ArrayList<LostItemDAO> getListOfLostItem();
    public ArrayList<LostItemDAO> deleteListOfLostItem();
    public ArrayList<LostItemDAO> insertListOfLostItem();
}
