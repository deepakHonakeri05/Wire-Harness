package com.hp.deepak.excelapp;

public class endListModel  {

    private String endItem,partNumber;

    private String qty;

    public endListModel(){ }

    public endListModel(String endItem,String partNumber,String qty){
        this.endItem=endItem;
        this.partNumber=partNumber;
        this.qty=qty;
    }

    public String getEndItem() {
        return endItem;
    }

    public void setEndItem(String endItem) {
        this.endItem = endItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getQty1() {
        return qty;
    }

    public void setQty1(String qty) {
        this.qty = qty;
    }
}
