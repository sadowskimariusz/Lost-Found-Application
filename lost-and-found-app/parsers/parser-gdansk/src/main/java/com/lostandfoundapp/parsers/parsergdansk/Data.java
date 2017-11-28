package com.lostandfoundapp.parsers.parsergdansk;

public class Data {

    String dateOfFinding;
    String dateOfReturn;
    String description;
    String placeOfFinding;

    public Data(){
        dateOfFinding=null;
        dateOfReturn=null;
        description=null;
        placeOfFinding=null;
    }

    public Data(Data a){
        dateOfFinding=a.dateOfFinding;
        dateOfReturn=a.dateOfReturn;
        description=a.description;
        placeOfFinding=a.placeOfFinding;
    }

    public String getDateOfFinding() {
        return dateOfFinding;
    }

    public void setDateOfFinding(String dateOfFinding) {
        this.dateOfFinding = dateOfFinding;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceOfFinding() {
        return placeOfFinding;
    }

    public void setPlaceOfFinding(String placeOfFinding) {
        this.placeOfFinding = placeOfFinding;
    }

    public void printDataInConsole(){
        System.out.print(dateOfFinding+"\n");
        System.out.print(dateOfReturn+"\n");
        System.out.print(description+"\n");
        System.out.print(placeOfFinding+"\n");
    }

    public void clearData(){
        dateOfFinding=null;
        dateOfReturn=null;
        description=null;
        placeOfFinding=null;
    }

}
