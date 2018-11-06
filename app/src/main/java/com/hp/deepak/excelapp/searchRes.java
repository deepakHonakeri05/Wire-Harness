package com.hp.deepak.excelapp;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class searchRes extends AppCompatActivity {

    private ListView listView,searchLV;

    private ArrayList<MyDataModel> ex1;
    private ArrayList<MyDataModel> searchResult1;
    private MyArrayAdapter adapter1;

    private ArrayList<endListModel> ex2;
    private ArrayList<endListModel> searchResult2;
    private endArrayAdapter adapter2;

    private ArrayList<wireListModel> ex3;
    private ArrayList<wireListModel> searchResult3;
    private wireListAdapter adapter3;

    private int sizeR;

    private ImageView connectorIV;

    Matrix matrix= new Matrix();
    float scale = 1f;
ScaleGestureDetector SGD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_res);

        Intent iv = getIntent();
        int id = iv.getIntExtra("ID",0);
        sizeR = iv.getIntExtra("size",0);
        String resTVs= iv.getStringExtra("result");

        ex1 = new ArrayList<>();
        ex1 = MainActivity.getAr1();
        searchResult1 = new ArrayList<>();
        adapter1= new MyArrayAdapter(this,ex1);

        ex2 = new ArrayList<>();
        ex2 = end_item_list.getAr2();
        searchResult2 = new ArrayList<>();
        adapter2= new endArrayAdapter(this,ex2);

        ex3 = new ArrayList<>();
        ex3 = wireList.getAr3();
        searchResult3 = new ArrayList<>();
        adapter3= new wireListAdapter(this,ex3);


        listView =  findViewById(R.id.searchLV);

        switch (id)
        {

            case 1 : printData1(resTVs);
                     break;
            case 2 : printData2(resTVs);
                     break;
            case 3 : printData4(resTVs);
                     break;
        }

    }


    private class ScaleListner extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale, scale);
            connectorIV.setImageMatrix(matrix);
            return true;
        }
    }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {

            SGD.onTouchEvent(event);
            return  true;

        }


    private void printData1 (String data)
    {
        searchResult1.clear();


        adapter1 = new MyArrayAdapter(this,searchResult1);
        searchLV=listView;

        //connectorIV.setVisibility(View.GONE);
        data=data.toLowerCase();


        for(int i=0;i<sizeR;i++){
            if(ex1.get(i).getPartno().toLowerCase().startsWith(data)){
                searchResult1.add(new MyDataModel(ex1.get(i).getPartno(),ex1.get(i).getQty(),ex1.get(i).getUom(),ex1.get(i).getDesc(),ex1.get(i).getBaps()));

            }
        };



        searchLV.setAdapter(adapter1);
    }

    private void printData2 (String data)
    {
        String endItem;
        searchResult2.clear();

        Log.e("Search Result","Result "+data);

        adapter2 = new endArrayAdapter(this,searchResult2);
        searchLV=listView;

        data=data.toLowerCase();

        for(int i=0;i<sizeR;i++){
            if(ex2.get(i).getPartNumber().toLowerCase().startsWith(data)){
                searchResult2.add(new endListModel(ex2.get(i).getEndItem(),ex2.get(i).getPartNumber(),ex2.get(i).getQty1()));

                endItem = ex2.get(i).getPartNumber().toLowerCase();

                switch (endItem)
                {

                    case "ms27488-22-2":setImage(1);
                        break;
                    case "794617-2": setImage(2);
                        break;

                    case "sjs830100":setImage(3);
                        break;
                }
            }
        };
        searchLV.setAdapter(adapter2);
    }

    private void printData4 (String data)
    {
        searchResult3.clear();

        adapter3 = new wireListAdapter(this,searchResult3);
        searchLV=listView;
//        connectorIV.setVisibility(View.GONE);


        data=data.toLowerCase();

        for(int i=0;i<sizeR;i++){
            if(ex3.get(i).getWire().toLowerCase().startsWith(data)){
                searchResult3.add(new wireListModel(ex3.get(i).getWire(),ex3.get(i).getMulticore(),ex3.get(i).getPartno(),ex3.get(i).getEndID1(),ex3.get(i).getPin1(),ex3.get(i).getTerm1(),
                        ex3.get(i).getEndID2(),ex3.get(i).getPin2(),ex3.get(i).getTerm2(),ex3.get(i).getLength(),ex3.get(i).getDesign()));
            }
        };
        searchLV.setAdapter(adapter3);

    }

    public void setImage(int id)
    {
        connectorIV = findViewById(R.id.connectorIV);

        SGD = new ScaleGestureDetector(this,new  ScaleListner());
        switch (id)
        {
            case 1:connectorIV.setImageResource(R.drawable.a4852p1);
                    break;
            case 2:connectorIV.setImageResource(R.drawable.ds5444j2);
                    break;
            case 3:connectorIV.setImageResource(R.drawable.ds5444p1);
                      break;
        }


    }

}
