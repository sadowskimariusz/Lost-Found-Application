package com.lostandfoundapp.service;

import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.warsaw.ParserWarsaw;
import com.lostandfoundapp.parsers.gdansk.ParserGdansk;
import com.lostandfoundapp.parsers.cracow.ParserCracow;
import com.lostandfoundapp.parsers.common.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Service {

    private List<Parser> parsers;
    private List<Item> items;

    public Service(List<Parser> parsers)throws IOException{

        this.parsers = parsers;
        items = new ArrayList<>();
    }

    public void downloadData(){

        Iterator<Parser> parserIterator = parsers.iterator();

        while(parserIterator.hasNext()){
            Parser currentParser = parserIterator.next();
            currentParser.parseData();
            items.addAll(items.size(), currentParser.getParsedData());
        }
    }

}

