package com.lostandfoundapp.parsers.common;

import com.lostandfoundapp.parsers.item.Item;

import java.util.List;

public interface Parser {

    List<Item> getParsedData();
    void parseData();

}
