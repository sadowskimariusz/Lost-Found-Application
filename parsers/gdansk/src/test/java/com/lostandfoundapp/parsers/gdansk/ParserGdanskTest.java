package com.lostandfoundapp.parsers.gdansk;

import com.lostandfoundapp.parsers.item.Item;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserGdanskTest {


    @Test
    public void getParsedData() throws Exception {
        ParserGdansk tester = new ParserGdansk("TestPage.html");

        tester.parseData();

        Item expected = new Item();

        expected.setURLAddressOfSource("TestPage.html");
        expected.setCityOfFound("Gdansk");
        expected.setItemID(0);
        expected.setNameOfItem("Kwota pieniędzy");
        expected.setPlaceOfFound("Gdańsk-Wrzeszcz");
        expected.setComment("");
        expected.setItemSourceID("");
        expected.setDateOfFinding("20.11.2017");

        List<Item> actualList = tester.getParsedData();

        Iterator<Item> actualListIterator = actualList.iterator();

        assertEquals(2, actualList.size());

        while(actualListIterator.hasNext()){

            Item actual = actualListIterator.next();

            assertEquals(expected.getURLAddressOfSource(),actual.getURLAddressOfSource());
            assertEquals(expected.getCityOfFound(),actual.getCityOfFound());
            assertEquals(expected.getItemID(), actual.getItemID());
            assertEquals(expected.getNameOfItem(), actual.getNameOfItem());
            assertEquals(expected.getPlaceOfFound(), actual.getPlaceOfFound());
            assertEquals(expected.getComment(), actual.getComment());
            assertEquals(expected.getItemSourceID(), actual.getItemSourceID());
            assertEquals(expected.getDateOfFinding(), actual.getDateOfFinding());
        }
    }
}