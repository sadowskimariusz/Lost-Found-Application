package com.lostandfoundapp.service;

import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;

import java.io.IOException;
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

    public List<Item> getItems() {
        return items;
    }

    private List<Item> items;

    public Service(List<Parser> parsers)throws IOException{

        this.parsers = parsers;
        items = new ArrayList<>();

    }

    @Scheduled(cron = "* */10 * * * *")
    public void downloadData(){

        Iterator<Parser> parserIterator = parsers.iterator();

        while(parserIterator.hasNext()){
            Parser currentParser = parserIterator.next();
            currentParser.parseData();
            items.addAll(currentParser.getParsedData());
        }
    }

    @RequestMapping("/")
    public int getAllItems(){

        return items.size();

    }


}

