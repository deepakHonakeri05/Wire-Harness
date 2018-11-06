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

public class wireList extends AppCompatActivity {

    private String url;

    private ListView listView;
    private static ArrayList<wireListModel> ex3;
    private ArrayAdapter<String> objAdapter;
    private Intent searchResInt;
    ArrayList<String> obj = new ArrayList<>();


    private int REQUEST_CODE=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Wire List");

        TextView partNo = findViewById(R.id.part_title);
        partNo.setText(Keys.WIRE);

        Intent iv = getIntent();
        url= iv.getStringExtra("url");

        ex3 = new ArrayList<>();
        searchResInt =  new Intent(getApplicationContext(),searchRes.class);
        listView =  findViewById(R.id.listView);
        objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, obj);

        FloatingActionButton fab1 =  findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                startVoiceRecognitionActivity();
            }
        });

        if (InternetConnection.checkConnection(getApplicationContext())) {
            GetDataTask_k gg = new GetDataTask_k();
            gg.execute();
        } else {
            Toast.makeText(this, "Internet Connection Not Available!", Toast.LENGTH_LONG).show();
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                searchResInt.putExtra("result",ex3.get(position).getWire());
                searchResInt.putExtra("ID",3);
                searchResInt.putExtra("size",ex3.size());
                startActivity(searchResInt);
            }
        });


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
                searchResInt.putExtra("ID",3);
                searchResInt.putExtra("size",ex3.size());
                startActivity(searchResInt);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static ArrayList<wireListModel> getAr3() {
        return ex3;
    }

    public class Keys {

        public static final String KEY_SHEETNAME = "WIRE LIST";
        public static final String WIRE = "WIRE";
        public static final String MULTICORE = "MULTICORE";
        public static final String PARTNO = "PART_NUMBER";
        public static final String END1 = "END_ID_1";
        public static final String PIN1 = "PIN_1";
        public static final String TERM1 = "TERM_1";
        public static final String END2 = "END_ID_2_";
        public static final String PIN2 = "PIN_2";
        public static final String TERM2 = "TERM_2";
        public static final String LENGTH = "LENGTH";
        public static final String DESIGN = "DESIGN";


    }

    class GetDataTask_k extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x=ex3.size();

            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(wireList.this);
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

                                wireListModel model = new wireListModel();

                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String  wire = innerObject.getString(Keys.WIRE);
                                String multicore = innerObject.getString(Keys.MULTICORE);
                                String partno = innerObject.getString(Keys.PARTNO);
                                String end1 = innerObject.getString(Keys.END1);
                                String pin1 = innerObject.getString(Keys.PIN1);
                                String term1 = innerObject.getString(Keys.TERM1);
                                String end2 = innerObject.getString(Keys.END2);
                                String pin2 = innerObject.getString(Keys.PIN2);
                                String term2 = innerObject.getString(Keys.TERM2);
                                String length = innerObject.getString(Keys.LENGTH);
                                String design = innerObject.getString(Keys.DESIGN);

                                model.setWire(wire);
                                model.setMulticore(multicore);
                                model.setPartno(partno);
                                model.setEndID1(end1);
                                model.setPin1(pin1);
                                model.setTerm1(term1);
                                model.setEndID2(end2);
                                model.setPin2(pin2);
                                model.setTerm2(term2);
                                model.setLength(length);
                                model.setDesign(design);

                                ex3.add(model);

                                wire = (jIndex+1)+ "      "+wire;
                                obj.add(wire);
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
            if(ex3.size() >0) {
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
            GetDataTask_k gg = new GetDataTask_k();
            gg.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
