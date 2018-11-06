package com.hp.deepak.excelapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class end_item_list extends AppCompatActivity {

    private ListView listView;
    private static ArrayList<endListModel> ex2;
    private ArrayAdapter<endModel2> objAdapter;

    ArrayList<endModel2> obj = new ArrayList<>();
    Intent searchResInt;

    private int REQUEST_CODE=3000;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("End Item List");


        Intent iv = getIntent();
        url= iv.getStringExtra("url");

        TextView partNo = findViewById(R.id.part_title);
        partNo.setText(Keys.KEY_PARTNO);

        TextView quantity = findViewById(R.id.qty_title);
        quantity.setText(Keys.KEY_QTY2);

        ex2 = new ArrayList<>();
        searchResInt =  new Intent(getApplicationContext(),searchRes.class);
        objAdapter = new endAdapter2(this,obj);
        listView =  findViewById(R.id.listView);

        FloatingActionButton fab1 =  findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                startVoiceRecognitionActivity();
            }
        });
        fab1.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                searchResInt.putExtra("result",""+ex2.get(position).getPartNumber());
                searchResInt.putExtra("ID",2);
                searchResInt.putExtra("size",ex2.size());
                //startActivity(searchResInt);

            }
        });


        if (InternetConnection.checkConnection(getApplicationContext())) {
            GetDataTask_H gg = new GetDataTask_H();
            gg.execute();
        } else {
            Toast.makeText(this, "Internet Connection Not Available!", Toast.LENGTH_LONG).show();
        }


    }

    public class Keys {

        public static final String KEY_SHEETNAME = "END ITEM LIST";
        public static final String KEY_ENDITEM = "END_ITEM";
        public static final String KEY_QTY2 = "QTY";
        public static final String KEY_PARTNO = "PART_NUMBER";

    }
    public static ArrayList<endListModel> getAr2() {
        return ex2;
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
                searchResInt.putExtra("result", result);
                searchResInt.putExtra("ID",2);
                searchResInt.putExtra("size",ex2.size());
                //startActivity(searchResInt);
                //printData(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class GetDataTask_H extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            x=ex2.size();

            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(end_item_list.this);
            dialog.setTitle("Loading ...");
            dialog.setMessage("getting the Excel file");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSONParser.getDataFromWeb(url);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_SHEETNAME);

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {

                                endListModel model = new endListModel();
                                endModel2 model_2 = new endModel2();

                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String enditem = innerObject.getString(Keys.KEY_ENDITEM);
                                String partno = innerObject.getString(Keys.KEY_PARTNO);
                                String qty = innerObject.getString(Keys.KEY_QTY2);


                                model.setPartNumber(partno);
                                model.setEndItem(enditem);
                                model.setQty1(qty);

                                enditem = "       "+enditem;
                                partno =  "      "+ partno;

                                model_2.setEndItem(enditem);
                                model_2.setPartNumber(partno);
                                model_2.setQty(qty);

                                ex2.add(model);
                                obj.add(model_2);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if(ex2.size() > 0) {
                //adapter1.notifyDataSetChanged();
                //Log.e(JSONParser.TAG,"print");
                listView.setAdapter(objAdapter);


            } else {
                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            GetDataTask_H gg = new GetDataTask_H();
            gg.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
