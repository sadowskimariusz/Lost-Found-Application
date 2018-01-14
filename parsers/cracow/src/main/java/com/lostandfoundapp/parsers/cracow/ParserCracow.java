package com.lostandfoundapp.parsers.cracow;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserCracow implements Parser {


    Document doc;

    List<DataStructure> dataList;
    List<String> pdfNames;

    String cityName;
    String source;

    public ParserCracow(){

        dataList = new ArrayList<DataStructure>();
        pdfNames = new ArrayList<String>();
        cityName = "Kraków";
        source = "https://www.bip.krakow.pl/?dok_id=19964";
    }

    private void connectToPage(String html) throws IOException {
        doc = Jsoup.connect(html).get();
    }

    private void getAllPDFs() throws IOException{

        Element body = doc.body();

        //Find all link form the parsercracow main site
        Elements elements = body.select("#mainDiv ul a[href]");
        List<String> href = new ArrayList<String>();

        for(Element a: elements){
            href.add(a.attr("abs:href"));
        }

        List<String> PDFhref = new ArrayList<String>();

        //Find all download links form sub-sites
        for(String a: href){

            doc = Jsoup.connect(a).get();

            Elements el = doc.select("tr.table_form a[href]");
            PDFhref.add(el.attr("abs:href"));


        }

        int i=0;
        for(String PDF: PDFhref){
            URL url = new URL(PDF);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("PDF nr" + Integer.toString(i)+".pdf");
            pdfNames.add("PDF nr" + Integer.toString(i)+".pdf");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            i++;
            fos.close();
        }

    }

    private void getDataFromPDFs() throws IOException{

        for(String a: pdfNames){

            PdfReader reader = new PdfReader(a);

            for(int i = 1; i<reader.getNumberOfPages()+1;i++){

                getDataFromPage(i,reader);
            }
        }

    }

    private void getDataFromPage(int page, PdfReader reader) throws IOException{

        String firstPageText = PdfTextExtractor.getTextFromPage(reader,page);

        String splitByLine[] = firstPageText.split("\n");

        for(int j=0; j<splitByLine.length;j++){

            String[] split = splitByLine[j].split(" ");

            char isNumber = split[0].charAt(0);

            if(isNumber < '0' || isNumber > '9' ){
                continue;
            }

            DataStructure temp = new DataStructure();

            temp.setCaseIndex(split[0]);

            temp.setCaseSymbol(split[1]);

            temp.setDateFound(split[split.length-1]);

            String itemDescription="";

            for(int i=2; i<split.length-1; i++){
                itemDescription += split[i] + " ";
            }

            temp.setItemName(itemDescription);

            dataList.add(new DataStructure(temp));

            temp.clear();
        }

    }

    public List<DataStructure> getDataList() {
        return dataList;
    }

    public void addPdfName(String name){
        pdfNames.add(name);
    }

    @Override
    public void parseData() {
        try {
            if(pdfNames.isEmpty()) {
                connectToPage(source);
                getAllPDFs();
            }
            getDataFromPDFs();
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    @Override
    public List<Item> getParsedData() {

        List<Item> itemList = new ArrayList<>();

        for(DataStructure data: dataList){
            Item temp = new Item();

            temp.setNameOfItem(data.getItemName());
            temp.setItemID(0);
            temp.setCityOfFound(cityName);
            temp.setPlaceOfFound("");
            temp.setComment(data.getCaseSymbol());
            temp.setItemSourceID(data.getCaseIndex());
            temp.setURLAddressOfSource(source);
            temp.setDateOfFinding(data.getDateFound());

            itemList.add(new Item(temp));

        }

        return itemList;
    }
}
