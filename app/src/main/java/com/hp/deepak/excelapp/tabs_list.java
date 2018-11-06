package com.hp.deepak.excelapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class tabs_list extends AppCompatActivity {


    private int REQUEST_CODE=3000;

    final String url_sheet1="https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1De6a1Jl20HQ_cfpVGvwRe23v7y16p_7Ar8zvUhFFLNk&sheet=TOTAL ITEM LIST";

    final String url_sheet2="https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1De6a1Jl20HQ_cfpVGvwRe23v7y16p_7Ar8zvUhFFLNk&sheet=END ITEM LIST";

    final String url_sheet3="https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1De6a1Jl20HQ_cfpVGvwRe23v7y16p_7Ar8zvUhFFLNk&sheet=WIRE LIST";

    private  Intent totalInt,endInt,wireInt,img3dInt,img2dInt,connectorInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_list);

        totalInt = new Intent(this,MainActivity.class);
        endInt = new Intent(this,end_item_list.class);
        wireInt = new Intent(this,wireList.class);
        img3dInt = new Intent(this,images.class);
        img2dInt = new Intent(this,images.class);
        connectorInt = new Intent(this,images.class);

       TextView totalTV= findViewById(R.id.total_list);
       totalTV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               totalInt();

           }
       });

        TextView endTV= findViewById(R.id.end_list);
        endTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            endInt();

            }
        });

        TextView wireTV= findViewById(R.id.wire_list);
        wireTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wireInt();
            }
        });

        FloatingActionButton fab1 =  findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                startVoiceRecognitionActivity();
            }
        });




        TextView img2dTv = findViewById(R.id.image2dIV);
        img2dTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2dInt();
            }
        });

        TextView connectotTV = findViewById(R.id.connectorimgIV);
        connectotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectorInt();
            }
        });

    }

    public void totalInt()
    {
        totalInt.putExtra("url",url_sheet1);
        startActivity(totalInt);
    }

    public void endInt()
    {
        endInt.putExtra("url",url_sheet2);
        startActivity(endInt);
    }

    public void wireInt()
    {
        wireInt.putExtra("url", url_sheet3);
        startActivity(wireInt);

    }

    public  void img3dInt()
    {

        img3dInt.putExtra("ImgID",R.drawable.g0914);
        img3dInt.putExtra("img_name","3D Drawing");
        startActivity(img3dInt);

    }

    public  void img2dInt()
    {

        img2dInt.putExtra("ImgID",R.drawable.end_connector);
        img2dInt.putExtra("img_name","2D Drawing");
        startActivity(img2dInt);
    }

    public  void connectorInt()

    {
        connectorInt.putExtra("img_name","ConnectorImages");
        connectorInt.putExtra("partID",1);
        connectorInt.putExtra("ImgID",R.drawable.ds5444p1);
        connectorInt.putExtra("img1",R.drawable.ds5444j2);
        connectorInt.putExtra("img2",R.drawable.a4852p1);
        startActivity(connectorInt);

    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Search parts ");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList< String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!matches.isEmpty())
            {
                String Query = matches.get(0);
                String result = Query.replaceAll("\\s", "");
                print(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void print(String data)
    {
        data=data.toLowerCase();

        switch (data)
        {
            case "totalitemlist": totalInt();
                                   break;
            case "enditemlist": endInt();
                                   break;
            case "wirelist": wireInt();
                                   break;

            case  "2ddrawing":

            case  "toddrawing": img2dInt();
                                  break;

            case "connectorimages": connectorInt();
                                    break;
        }

    }



}
