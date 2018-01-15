package com.lostandfoundapp.service;

import com.lostandfoundapp.dao.LostItemDAOimpl;
import com.lostandfoundapp.dao.lostitem.LostItem;
import com.lostandfoundapp.dao.lostitemoperations.LostItemDAO;
import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

    @Autowired
    private List<Parser> parsers;

    @Autowired
    private LostItemDAO dao;

    @Autowired
    ItemConverter converter;

    public Service(List<Parser> parsers)throws IOException{

        this.parsers = parsers;
    }

    @PostConstruct
    @Scheduled(cron = "* */10 * * * *")
    public void downloadData() throws SQLException, ClassNotFoundException {

        Iterator<Parser> parserIterator = parsers.iterator();

        //dao = new LostItemDAOimpl();
        //converter = new ItemConverter();

        dao.deleteListOfLostItem();

        while(parserIterator.hasNext()){
            Parser currentParser = parserIterator.next();
            currentParser.parseData();

            dao.insertListOfLostItem( converter.convertItemToLostItem(currentParser.getParsedData()));
        }
    }

    @RequestMapping("/")
    public String getAllItems() throws SQLException, ClassNotFoundException{

        List<LostItem> daoItemList = dao.getListOfLostItem();

        String finalMessage = "";

        Iterator<LostItem> itemIterator = daoItemList.iterator();

        finalMessage += "   City    |   Date    |   Name    |   Place of finding \n";

        while(itemIterator.hasNext()){
            LostItem current = itemIterator.next();

            finalMessage += current.getCityOfFound() + " ";
            finalMessage += current.getDateOfFinding() + " ";
            finalMessage += current.getNameOfItem() + " ";
            finalMessage +=  current.getPlaceOfFound() + " ";
            finalMessage += "\n";
        }

        return finalMessage;
    }


}

