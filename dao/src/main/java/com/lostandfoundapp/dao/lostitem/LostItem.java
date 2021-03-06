package com.lostandfoundapp.dao.lostitem;

import org.springframework.stereotype.Component;

@Component
public class LostItem {

    private String nameOfItem;
    private int itemID; //this ID is generated by our programme
    private String cityOfFound;
    private String placeOfFound;
    private String comment;
    private String itemSourceID; //this ID is received specific site
    private String URLAddressOfSource;
    private String dateOfFinding;


    public String getDateOfFinding() {
        return dateOfFinding;
    }

    public void setDateOfFinding(String dateOfFinding) {
        this.dateOfFinding = dateOfFinding;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getCityOfFound() {
        return cityOfFound;
    }

    public void setCityOfFound(String cityOfFound) {
        this.cityOfFound = cityOfFound;
    }

    public String getPlaceOfFound() {
        return placeOfFound;
    }

    public void setPlaceOfFound(String placeOfFound) {
        this.placeOfFound = placeOfFound;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String commment) {
        this.comment = commment;
    }

    public String getItemSourceID() {
        return itemSourceID;
    }

    public void setItemSourceID(String itemSourceID) {
        this.itemSourceID = itemSourceID;
    }

    public String getURLAddressOfSource() {
        return URLAddressOfSource;
    }

    public void setURLAddressOfSource(String URLAddressOfSource) {
        this.URLAddressOfSource = URLAddressOfSource;
    }
}
