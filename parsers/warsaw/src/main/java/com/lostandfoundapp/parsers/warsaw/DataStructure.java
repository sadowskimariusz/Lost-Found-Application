package com.lostandfoundapp.parsers.warsaw;

public class DataStructure {

    private String nameOfItem;
    private String itemID;
    private String dateOfFound;
    private String cityOfFound;

    public String getNameOfItem() {
        return nameOfItem;
    }

    public String getItemID() {
        return itemID;
    }

    public String getDateOfFound() {
        return dateOfFound;
    }

    public String getCityOfFound() {
        return cityOfFound;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setDateOfFound(String dateOfFound) {
        this.dateOfFound = dateOfFound;
    }

    public void setCityOfFound(String cityOfFound) {
        this.cityOfFound = cityOfFound;
    }

    public void printOnConsole(){
        System.out.print(itemID + " "+ dateOfFound + " " + nameOfItem + " " + cityOfFound + "\n");
    }
}

