package com.lostandfoundapp.service;

import com.lostandfoundapp.dao.lostitem.LostItem;
import com.lostandfoundapp.parsers.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {

    public List<LostItem> convertItemToLostItem(List<Item> itemList) {

        List<LostItem> lostItemList = new ArrayList<>();

        for (Item item : itemList ) {

            LostItem tempLostItem = new LostItem();

            tempLostItem.setCityOfFound(item.getCityOfFound());
            tempLostItem.setComment(item.getComment());
            tempLostItem.setDateOfFinding(item.getDateOfFinding());
            tempLostItem.setItemSourceID(item.getItemSourceID());
            tempLostItem.setNameOfItem(item.getNameOfItem());
            tempLostItem.setPlaceOfFound(item.getPlaceOfFound());
            tempLostItem.setURLAddressOfSource(item.getURLAddressOfSource());

            lostItemList.add(tempLostItem);

        }
        return lostItemList;
    }



}
