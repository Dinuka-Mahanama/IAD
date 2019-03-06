package com.creedtech.project.iad.iadproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Payments extends AppCompatActivity {

    ListView PAYFormListView;
    ProgressBar progressBar2;
    String HttpURL = "https://creedtech.org/iadproject/testing/test1.inc.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payments);

        PAYFormListView = (ListView) findViewById(R.id.PAYFormListView);

        progressBar2 = (ProgressBar) findViewById(R.id.ProgressBar2);

        new ParseJSonDataClass(this).execute();
    }

    public void onBackPressed() {
        Thread myThread = new Thread(){
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(),Mainactivity.class);
                startActivity(intent);
                finish();
            }
        };
        myThread.start();
    }

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context text;
        String FinalJSonResult;
        List<PAYClass> SubjectFullFormList;

        public ParseJSonDataClass(Context text) {

            this.text = text;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();
                    Log.d("myTag", FinalJSonResult);

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;
                            PAYClass subject;

                            SubjectFullFormList = new ArrayList<PAYClass>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                subject = new PAYClass();

                                jsonObject = jsonArray.getJSONObject(i);

                                subject.Transaction_ID = jsonObject.getString("Transaction_ID");

                                subject.Amount = jsonObject.getString("Amount");

                                subject.Bank = jsonObject.getString("Bank");

                                subject.Account_No = jsonObject.getString("Account_No");

                                subject.Customer_ID = jsonObject.getString("Customer_ID");

                                subject.PayType = jsonObject.getString("PayType");

                                SubjectFullFormList.add(subject);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(text, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar2.setVisibility(View.GONE);

            PAYFormListView.setVisibility(View.VISIBLE);

            if (SubjectFullFormList != null) {

                PAYAdapter ter = new PAYAdapter(SubjectFullFormList, text);

               PAYFormListView.setAdapter(ter);
            }
        }
    }
}
