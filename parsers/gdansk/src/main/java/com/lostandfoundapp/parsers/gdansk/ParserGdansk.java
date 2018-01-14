package com.lostandfoundapp.parsers.gdansk;


import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ParserGdansk implements Parser{
    Document doc;
    List<Data> dataList;

    String cityName;

    String source;

    //Constructor which connect to provided site
    public ParserGdansk(String html){
        dataList = new ArrayList<Data>();
        dataList.clear();
        cityName = "Gdansk";
        source = html;
    }

    //Default constructor that connect to bip.gdansk.pl
    public ParserGdansk(){
        dataList = new ArrayList<Data>();
        dataList.clear();
        cityName = "Gdansk";
        source = "http://bip.gdansk.pl/urzad-miejski/Informacje-biura-rzeczy-znalezionych,a,50360";
    }

    private void connectToHTMLPage() throws IOException {

        doc = Jsoup.connect(source).get();
    }

    private void connectToLocalHTML() throws IOException{
        File input = new File(source);
        doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
    }

    private void fillDataTable(){

        Elements table = doc.body().select("table td");

        int skipCount=6;
        int informationRead=0;

        int year = Calendar.getInstance().get(Calendar.YEAR);
        --year;

        Data temp = new Data();

        for(Element el: table){

            if(el.text().equals("")){
                continue;
            }
            if(el.text().equalsIgnoreCase("Rok " + Integer.toString(year))){
                year--;
                continue;
            }

            if(skipCount>0){
                skipCount--;
                continue;
            }

            if(informationRead==0){
                temp.setDateOfFinding(el.text());
                informationRead++;
                continue;
            }
            if(informationRead==1){
                temp.setDateOfReturn(el.text());
                informationRead++;
                continue;
            }
            if (informationRead==2){
                temp.setDescription(el.text());
                informationRead++;
                continue;
            }
            if(informationRead==3){
                temp.setPlaceOfFinding(el.text());
                informationRead=0;
                dataList.add( new Data(temp));
                temp.clearData();
                continue;
            }

        }
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    private List<Item> convertToItemList(){
        List<Item> itemList = new ArrayList<>();

        for(Data a: dataList){
            Item temp = new Item();

            temp.setNameOfItem(a.getDescription());
            temp.setItemID(0);
            temp.setCityOfFound(cityName);
            temp.setPlaceOfFound(a.getPlaceOfFinding());
            temp.setComment("");
            temp.setItemSourceID("");
            temp.setURLAddressOfSource(source);
            temp.setDateOfFinding(a.getDateOfFinding());

            itemList.add(new Item(temp));

        }

        return itemList;
    }

    @Override
    public void parseData(){
        if(source.equals("http://bip.gdansk.pl/urzad-miejski/Informacje-biura-rzeczy-znalezionych,a,50360")) {
            try {
                connectToHTMLPage(); //Connect to a site
            } catch (IOException e) {
                System.out.print("Html does not correspond to a site");
            }
        }else {
            try {
                connectToLocalHTML(); //If above fails, check if it's a local file
            } catch (IOException e) {
                System.out.print("Site is not a local file");
            }
        }


        fillDataTable();

    }

    @Override
    public List<Item> getParsedData() {

        return convertToItemList();
    }
}

