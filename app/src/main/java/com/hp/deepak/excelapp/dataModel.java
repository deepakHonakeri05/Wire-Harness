package com.hp.deepak.excelapp;

public class dataModel {

    private String partno,qty,uom,desc,baps;

    public dataModel() {
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBaps() {
        return baps;
    }

    public void setBaps(String baps) {
        this.baps = baps;
    }
}