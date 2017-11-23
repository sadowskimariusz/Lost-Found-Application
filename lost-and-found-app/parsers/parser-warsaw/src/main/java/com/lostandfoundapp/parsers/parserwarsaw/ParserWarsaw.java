package com.lostandfoundapp.parsers.parserwarsaw;


import com.lostandfoundapp.parsers.parsercommon.Parser;

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
}
