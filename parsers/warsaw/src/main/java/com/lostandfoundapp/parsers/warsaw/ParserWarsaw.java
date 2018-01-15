package com.lostandfoundapp.parsers.warsaw;

import com.lostandfoundapp.parsers.common.Parser;
import com.lostandfoundapp.parsers.item.Item;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ParserWarsaw implements Parser {

    private Document document;

    private List<DataStructure> dataStructureList;
    final private String URLsourceAddress;
    private List<String> URLDownloadAddresses;
    private List<String> XLSFilesNames;

    public List<String> getURLDownloadAddresses() {
        return URLDownloadAddresses;
    }

    public List<DataStructure> getDataStructureList() {
        return dataStructureList;
    }

    public ParserWarsaw() throws IOException {

        XLSFilesNames = new ArrayList<>();
        dataStructureList = new ArrayList<>();
        URLsourceAddress = "https://bip.warszawa.pl/Menu_przedmiotowe/ogloszenia/rzeczy_znalezione";
        URLDownloadAddresses = new ArrayList<>();
        //document = Jsoup.parse( new File("html_file_TEST.html"), "UTF-8");


//        connectToHTMLPage(URLsourceAddress);
//        getUrlDownloadAddresses();
//        generateXlsFiles();
//        getDataFromAllXlsFiles(XLSFilesNames);

    }

    public void useDocumentForTests() throws IOException {
        document = Jsoup.parse( new File("html_file_TEST.html"), "UTF-8");
    }

    private void connectToHTMLPage(String URLaddress) throws IOException {

        document = Jsoup.connect(URLaddress).validateTLSCertificates(false).get();
    }

    protected void getUrlDownloadAddresses() {

        Element body = document.body();

        //Find all download links from warsaw main site
        Elements elements = body.select("a:contains(rzeczy znalezionych)");


        for (Element element : elements) {
            URLDownloadAddresses.add(element.attr("abs:href"));
        }
        //System.out.println(URLDownloadAddresses);
    }

    private void generateXlsFiles() throws IOException {
        int i = 0;
        for (String URLDownloadAddress : URLDownloadAddresses) {
            XLSFilesNames.add("lost_things_" + i + ".xls");
            FileUtils.copyURLToFile(new URL(URLDownloadAddress), new File(XLSFilesNames.get(i)));
            ++i;
        }
    }

    protected void getDataFromAllXlsFiles(List<String> fileNames) throws IOException {

        for (String XLSFileName : fileNames) {

            FileInputStream fis = new FileInputStream(XLSFileName);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            iterator.next();

            while (iterator.hasNext()) {

                DataStructure temp = new DataStructure();


                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Cell currentCell = cellIterator.next();
                temp.setItemID(currentCell.getStringCellValue());

                if(!cellIterator.hasNext()) break;
                currentCell = cellIterator.next();
                DataFormatter df = new DataFormatter();
                String value = df.formatCellValue(currentCell);
                temp.setDateOfFound(value);

                if(!cellIterator.hasNext()) break;
                cellIterator.next();

                currentCell = cellIterator.next();
                temp.setNameOfItem(currentCell.getStringCellValue());

                if(!cellIterator.hasNext()) break;
                currentCell = cellIterator.next();
                temp.setCityOfFound(currentCell.getStringCellValue());

                dataStructureList.add(temp);
            }

          /* for (DataStructure dataStructure : dataStructureList) {
                dataStructure.printOnConsole();
            }*/

            workbook.close();
            fis.close();
        }
//        System.out.println(dataStructureList.size());
    }

    @Override
    public void parseData() {
        try {
            connectToHTMLPage(URLsourceAddress);
            if (XLSFilesNames.isEmpty()) {
                getUrlDownloadAddresses();
                generateXlsFiles();
                getDataFromAllXlsFiles(XLSFilesNames);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getParsedData() {
        List<Item> itemList = new ArrayList<>();

        for (DataStructure dataStructureItem : dataStructureList) {

            Item temp = new Item();

            temp.setNameOfItem(dataStructureItem.getNameOfItem());
            temp.setItemID(0);
            temp.setCityOfFound(dataStructureItem.getCityOfFound());
            temp.setPlaceOfFound("");
            temp.setComment("");
            temp.setItemSourceID(dataStructureItem.getItemID());
            temp.setURLAddressOfSource(URLsourceAddress);
            temp.setDateOfFinding(dataStructureItem.getDateOfFound());

            itemList.add(new Item(temp));
        }

        //System.out.println(itemList.size());
        return itemList;
    }
}
