package com.lostandfoundapp.parsers.parsercommon;

import com.lostandfoundapp.parsers.Item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariachi on 23.11.2017.
 */
public interface Parser {

    List<Item> getParsedData();
    void parseData();

}
