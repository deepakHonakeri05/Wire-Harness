package com.hp.deepak.excelapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class main_task extends AppCompatActivity {

    private int REQUEST_CODE=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_task);

        Button bv = findViewById(R.id.button);
        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             tabsInt();
            }
        });

        FloatingActionButton fab1 =  findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                startVoiceRecognitionActivity();

            }
        });

    }

    private void tabsInt()
    {
        Intent iv = new Intent(getApplicationContext(),tabs_list.class);
        startActivity(iv);
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
                result=result.toLowerCase();
                startAct(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void startAct(String result)
    {
        Log.e("Main Task","Result "+result);

          switch (result)
          {
              case "g09149329-001" : tabsInt();
                                     break;
              case "gta":tabsInt();
                                     break;

              default: Log.e("Main Task ","Case not matched");
          }

    }
}
