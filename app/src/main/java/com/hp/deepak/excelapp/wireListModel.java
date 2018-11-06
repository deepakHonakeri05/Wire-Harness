package com.hp.deepak.excelapp;

public class wireListModel {

    private String wire,multicore,partno,endID1,pin1,term1,endID2,pin2,term2,length,design;


    public wireListModel(){

    }

    public wireListModel(String wire,String multicore, String partno,String endID1,String pin1,
                          String term1,String endID2,String pin2,String term2,String length,String design)
    {
      this.wire= wire;
      this.multicore=multicore;
      this.partno=partno;
      this.endID1=endID1;
      this.pin1=pin1;
      this.term1=term1;
      this.endID2=endID2;
      this.pin2=pin2;
      this.term2=term2;
      this.length=length;
      this.design=design;
    }

    public String getWire() {
        return wire;
    }

    public void setWire(String wire) {
        this.wire = wire;
    }

    public String getMulticore() {
        return multicore;
    }

    public void setMulticore(String multicore) {
        this.multicore = multicore;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public String getEndID1() {
        return endID1;
    }

    public void setEndID1(String endID1) {
        this.endID1 = endID1;
    }

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    public String getTerm1() {
        return term1;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public String getEndID2() {
        return endID2;
    }

    public void setEndID2(String endID2) {
        this.endID2 = endID2;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public String getTerm2() {
        return term2;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }
}
