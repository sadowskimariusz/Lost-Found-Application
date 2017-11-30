package com.lostandfoundapp.parsers.cracow;

public class DataStructure {


    private String caseIndex;
    private String caseSymbol;
    private String itemName;
    private String dateFound;

    public DataStructure(){
        caseSymbol = "";
        caseIndex = "";
        itemName = "";
        dateFound = "";
    }

    public DataStructure(DataStructure a){
        caseSymbol = a.getCaseSymbol();
        caseIndex = a.getCaseIndex();
        itemName = a.getItemName();
        dateFound = a.getDateFound();
    }

    public void clear(){
        caseSymbol = "";
        caseIndex = "";
        itemName = "";
        dateFound = "";
    }

    public String getCaseIndex() {
        return caseIndex;
    }

    public void setCaseIndex(String caseIndex) {
        this.caseIndex = caseIndex;
    }

    public String getCaseSymbol() {
        return caseSymbol;
    }

    public void setCaseSymbol(String caseSymbol) {
        this.caseSymbol = caseSymbol;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDateFound() {
        return dateFound;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public void printOnConsole(){
        System.out.print(caseIndex + " "+ caseSymbol + " " + itemName + " " + dateFound + "\n");
    }
}

