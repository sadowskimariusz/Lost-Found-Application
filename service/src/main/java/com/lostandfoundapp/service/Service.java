package com.lostandfoundapp.service;

import com.lostandfoundapp.dao.LostItemDAOimpl;
import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Service {

    private List<Parser> parsers;

    private LostItemDAOimpl dao;

    ItemConverter converter;

    public List<Item> getItems() {
        return items;
    }

    private List<Item> items;

    public Service(List<Parser> parsers)throws IOException{

        this.parsers = parsers;
        items = new ArrayList<>();
    }

    public void downloadData() throws SQLException, ClassNotFoundException {

        Iterator<Parser> parserIterator = parsers.iterator();

        dao = new LostItemDAOimpl();
        converter = new ItemConverter();

        while(parserIterator.hasNext()){
            Parser currentParser = parserIterator.next();
            currentParser.parseData();


            dao.insertListOfLostItem( converter.convertItemToLostItem(currentParser.getParsedData()));
        }
    }


}

