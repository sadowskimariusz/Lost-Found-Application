package com.lostandfoundapp.parsers.warsaw;


import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;

import java.util.List;

/**
 * Created by Mariachi on 23.11.2017.
 */
public class ParserWarsaw implements Parser {
    public ParserWarsaw() {

        System.out.println("im from parser warsaw");
        this.pirntsmth();
    }

    public void pirntsmth() {
        System.out.println("im from interface");
    }

    public void parseData() {

    }

    public List<Item> getParsedData() {
        return null;
    }
}
