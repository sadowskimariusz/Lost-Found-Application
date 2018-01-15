package com.lostandfoundapp.parsers.warsaw;

import com.lostandfoundapp.parsers.item.Item;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ParserWarsawTest {

    @Test
    public void getParsedData() throws IOException {

        ParserWarsaw tester = new ParserWarsaw();
        tester.useDocumentForTests();
        tester.getUrlDownloadAddresses();

        List<String> ExpectedUrlDownloadAddressList = new ArrayList<>();
        ExpectedUrlDownloadAddressList.add("https://bip.warszawa.pl/NR/rdonlyres/CF8D75DA-6311-4BD9-A7C7-5194409631BE/1314692/Wykazrzeczyznalezionych2015rpo21czerwca2015rBiP6.xls");
        ExpectedUrlDownloadAddressList.add("https://bip.warszawa.pl/NR/rdonlyres/CF8D75DA-6311-4BD9-A7C7-5194409631BE/1298123/Wykazrzeczyznalezionych2016BIP38.xls");
        ExpectedUrlDownloadAddressList.add("https://bip.warszawa.pl/NR/rdonlyres/CF8D75DA-6311-4BD9-A7C7-5194409631BE/1314691/Wykazrzeczyznalezionych2017rBIP42.xls");

        assertEquals(ExpectedUrlDownloadAddressList.get(0), tester.getURLDownloadAddresses().get(0));
        assertEquals(ExpectedUrlDownloadAddressList.get(1), tester.getURLDownloadAddresses().get(1));
        assertEquals(ExpectedUrlDownloadAddressList.get(2), tester.getURLDownloadAddresses().get(2));


        List<String> xlsFileNameList = new ArrayList<>();
        xlsFileNameList.add("lost_things_0_TEST.xls");
        xlsFileNameList.add("lost_things_1_TEST.xls");
        xlsFileNameList.add("lost_things_2_TEST.xls");

        tester.getDataFromAllXlsFiles(xlsFileNameList);

        DataStructure expectedFirstElement = new DataStructure();

        expectedFirstElement.setItemID("1.");
        expectedFirstElement.setDateOfFound("14 czerwca 2015");
        expectedFirstElement.setNameOfItem("torba z laptopem");
        expectedFirstElement.setCityOfFound("Warszawa");

        DataStructure actualFirstElement = tester.getDataStructureList().get(0);

        assertEquals(expectedFirstElement.getItemID(), actualFirstElement.getItemID());
        assertEquals(expectedFirstElement.getNameOfItem(), actualFirstElement.getNameOfItem());
        assertEquals(expectedFirstElement.getDateOfFound(), actualFirstElement.getDateOfFound());
        assertEquals(expectedFirstElement.getCityOfFound(), actualFirstElement.getCityOfFound());

        DataStructure expectedLastElement = new DataStructure();

        expectedLastElement.setItemID("1887.");
        expectedLastElement.setDateOfFound("8 grudnia 2017");
        expectedLastElement.setNameOfItem("telefon");
        expectedLastElement.setCityOfFound("Warszawa");

        DataStructure actualLastElement = tester.getDataStructureList().get(tester.getDataStructureList().size()-1);

        assertEquals(expectedLastElement.getItemID(), actualLastElement.getItemID());
        assertEquals(expectedLastElement.getNameOfItem(), actualLastElement.getNameOfItem());
        assertEquals(expectedLastElement.getDateOfFound(), actualLastElement.getDateOfFound());
        assertEquals(expectedLastElement.getCityOfFound(), actualLastElement.getCityOfFound());


        int expectedSizeOfDataStructureList = 6516;
        assertEquals(expectedSizeOfDataStructureList, tester.getDataStructureList().size());

        tester.getParsedData();

        Item expectedFirstItem = new Item();

        expectedFirstItem.setNameOfItem("torba z laptopem");
        expectedFirstItem.setDateOfFinding("14 czerwca 2015");
        expectedFirstItem.setItemSourceID("1.");
        expectedFirstItem.setCityOfFound("Warszawa");
        expectedFirstItem.setItemID(0);
        expectedFirstItem.setPlaceOfFound("");
        expectedFirstItem.setComment("");
        expectedFirstItem.setItemSourceID("https://bip.warszawa.pl/Menu_przedmiotowe/ogloszenia/rzeczy_znalezione");







    }
}
