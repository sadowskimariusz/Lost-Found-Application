package com.lostandfoundapp.parsers.cracow;


import com.lostandfoundapp.parsers.item.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserCracowTest {
    @Test
    public void getParsedData() throws Exception {

        ParserCracow tester = new ParserCracow();

        tester.addPdfName("testPDF.pdf");

        tester.parseData();

        Item expected = new Item();

        expected.setNameOfItem("SASZETKA Z ZAW. ");
        expected.setItemID(0);
        expected.setCityOfFound("Kraków");
        expected.setPlaceOfFound("");
        expected.setComment("SA-03.5314.1.1.2017");
        expected.setItemSourceID("1.");
        expected.setURLAddressOfSource("https://www.bip.krakow.pl/?dok_id=19964");
        expected.setDateOfFinding("13-12-2016");

        Item actual = tester.getParsedData().iterator().next();

        assertEquals(expected.getNameOfItem(), actual.getNameOfItem());
        assertEquals(expected.getItemID(), actual.getItemID());
        assertEquals(expected.getCityOfFound(), actual.getCityOfFound());
        assertEquals(expected.getPlaceOfFound(), actual.getPlaceOfFound());
        assertEquals(expected.getComment(), actual.getComment());
        assertEquals(expected.getItemSourceID(), actual.getItemSourceID());
        assertEquals(expected.getURLAddressOfSource(), actual.getURLAddressOfSource());
        assertEquals(expected.getDateOfFinding(), actual.getDateOfFinding());

    }
}

