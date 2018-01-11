package com.lostandfoundapp.service;

import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.common.Parser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/service")
public class Service {

    private List<Parser> parsers;

    public List<Item> getItems() {
        return items;
    }

    private List<Item> items;

    public Service(List<Parser> parsers)throws IOException{

        this.parsers = parsers;
        items = new ArrayList<>();

    }

    public void createFakeItemList(){
        List<Item> fakeList = new ArrayList<>();
        Item fake = new Item();
        fakeList.add(fake);

        items=fakeList;

    }

    public void downloadData(){

        Iterator<Parser> parserIterator = parsers.iterator();

        while(parserIterator.hasNext()){
            Parser currentParser = parserIterator.next();
            currentParser.parseData();
            items.addAll(currentParser.getParsedData());
        }
    }

    @GET
    @Path("/items")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllItems(){

        if(items.isEmpty()){

            return "Nothing to show";
        }else{
            return "I work!";
        }
        /*
        return "---Item List---\n"
                + items.stream()
                .map(c -> c.toString())
                .collect(Collectors.joining("\n"));
*/
    }


}

