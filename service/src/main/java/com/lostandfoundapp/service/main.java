package com.lostandfoundapp.service;

import com.lostandfoundapp.parsers.common.Parser;
import com.lostandfoundapp.parsers.cracow.ParserCracow;
import com.lostandfoundapp.parsers.gdansk.ParserGdansk;
import com.lostandfoundapp.parsers.warsaw.ParserWarsaw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

        try {

            Service service;

<<<<<<< Updated upstream
            ParserWarsaw warsaw = new ParserWarsaw();
            ParserGdansk gdansk = new ParserGdansk();
=======
            //ParserWarsaw warsaw = new ParserWarsaw();
>>>>>>> Stashed changes
            ParserCracow cracow = new ParserCracow();
            ParserGdansk gdansk = new ParserGdansk();

            List<Parser> parsers = new ArrayList<>();

            parsers.add(cracow);
            parsers.add(gdansk);
            parsers.add(warsaw);


            service = new Service(parsers);

            service.downloadData();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
