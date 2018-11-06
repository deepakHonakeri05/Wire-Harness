package com.hp.deepak.excelapp;

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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView,searchLV;
    private static ArrayList<MyDataModel> ex1;
    private ArrayAdapter<String> objAdapter;
    private ArrayList<String> obj = new ArrayList<>();
    private ArrayList<String> searchRes = new ArrayList<>();


    private int REQUEST_CODE=3000;
    String url;
    Intent searchResInt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Total Item List");


        Intent iv = getIntent();
        url= iv.getStringExtra("url");

        ex1 = new ArrayList<>();

        TextView partNo = findViewById(R.id.part_title);
        partNo.setText(Keys.KEY_PARTNUMBER);

        objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, obj);
        listView =  findViewById(R.id.listView);
        searchResInt =  new Intent(getApplicationContext(),searchRes.class);


        FloatingActionButton fab1 =  findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                startVoiceRecognitionActivity();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchResInt.putExtra("result",""+ex1.get(position).getPartno());
                searchResInt.putExtra("ID",1);
                searchResInt.putExtra("size",ex1.size());
                startActivity(searchResInt);

            }
        });


        if (InternetConnection.checkConnection(getApplicationContext())) {
            new GetDataTask().execute();
        } else {

            Toast.makeText(this, "Internet Connection Not Available!", Toast.LENGTH_LONG).show();
        }

    }

    public static ArrayList<MyDataModel> getAr1() {

        return ex1;
    }

    public class Keys {

        public static final String KEY_SHEETNAME = "TOTAL ITEM LIST";
        public static final String KEY_PARTNUMBER = "PART_NUMBER";
        public static final String KEY_QTY = "QTY";
        public static final String KEY_UOM = "UOM";
        public static final String KEY_DESCRIPTION = "DESCRIPTION";
        public static final String KEY_BAPS= "BAPS";

    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Search parts ");
        startActivityForResult(intent, REQUEST_CODE);
    }
    /**
     * Handle the results from the voice recognition activity.
     */
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
                searchResInt.putExtra("ID",1);
                searchResInt.putExtra("size",7);
                startActivity(searchResInt);
                //printData(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x=ex1.size();

            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(MainActivity.this);
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

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                MyDataModel model = new MyDataModel();

                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String partno = innerObject.getString(Keys.KEY_PARTNUMBER);
                                String qty = innerObject.getString(Keys.KEY_QTY);
                                String uom = innerObject.getString(Keys.KEY_UOM);
                                String desc = innerObject.getString(Keys.KEY_DESCRIPTION);
                                String baps = innerObject.getString(Keys.KEY_BAPS);


                                model.setPartno(partno);
                                model.setQty(qty);
                                model.setUom(uom);
                                model.setDesc(desc);
                                model.setBaps(baps);

                                ex1.add(model);


                                partno=(jIndex+1)+ "      "+partno;
                                obj.add(partno);

                            }
                        }
                    }
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
            if(ex1.size() > 0) {
                //adapter1.notifyDataSetChanged();
                Log.e(JSONParser.TAG,"print");
                listView.setAdapter(objAdapter);


            } else {
                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

 private void printData(String data)
 {
 searchLV=listView;


     for(int i=0;i<ex1.size();i++){
         if(ex1.get(i).getPartno().toLowerCase().startsWith(data)){
             searchRes.add(ex1.get(i).getPartno());
         }

         searchLV.setAdapter(objAdapter);
         searchLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 searchResInt.putExtra("result",""+ex1.get(position).getPartno());
                 searchResInt.putExtra("ID",1);
                 searchResInt.putExtra("size",ex1.size());
                 startActivity(searchResInt);

             }
         });

     };
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
            new GetDataTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
