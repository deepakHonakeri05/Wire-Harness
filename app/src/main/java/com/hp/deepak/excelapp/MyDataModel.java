package com.hp.deepak.excelapp;

public class MyDataModel {

    private String partno,qty,uom,desc,baps;

    public MyDataModel() {
    }

    public MyDataModel(String partno,String qty,String uom ,String desc ,String baps) {

        this.partno=partno;
        this.qty=qty;
        this.uom=uom;
        this.desc=desc;
        this.baps=baps;

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
