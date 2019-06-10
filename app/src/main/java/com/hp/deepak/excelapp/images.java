package com.hp.deepak.excelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

public class images extends AppCompatActivity {

    String title;
    String imgTV,img1Tv,img2Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_activity);


         TextView imgTv = findViewById(R.id.drawingImg1TV);

        Intent iv = getIntent();
        title = iv.getStringExtra("img_name");
        setTitle(title);
        int imgId = iv.getIntExtra("ImgID", 0);
        int img1Id = iv.getIntExtra("img1", 0);
        int img2Id = iv.getIntExtra("img2", 0);
        int partId = iv.getIntExtra("partID", 0);

        imgTV = "DS5444P1";
        img1Tv = "DS5444J2";
        img2Tv = "A4852P1";

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(imgId);
        imgTv.setText("g09149329-001");

        //ImageView drawingimgIv = findViewById(R.id.drawingImg);
        //drawingimgIv.setImageResource(imgId);


        PhotoView img1Iv = findViewById(R.id.drawingImg1);
        img1Iv.setImageResource(img1Id);

        PhotoView img2Iv = findViewById(R.id.drawingImg2);
        img2Iv.setImageResource(img2Id);

        if (partId == 1) {
            imgTv.setText(imgTV);

            TextView imgTv1 = findViewById(R.id.img1TV);
            imgTv1.setText(img1Tv);

            TextView imgTv2 = findViewById(R.id.img2TV);
            imgTv2.setText(img2Tv);


        }

    }


}
