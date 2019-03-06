package com.creedtech.project.iad.iadproject;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ClaimHistory extends AppCompatActivity {

    ListView SubjectFullFormListView;
    ProgressBar progressBar;
    String HttpURL = "https://creedtech.org/iadproject/testing/test2.inc.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_claimhistory);

        SubjectFullFormListView = (ListView) findViewById(R.id.SubjectFullFormListView);

        progressBar = (ProgressBar) findViewById(R.id.ProgressBar1);

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
        public Context context;
        String FinalJSonResult;
        List<CHClass> SubjectFullFormList;

        public ParseJSonDataClass(Context context) {

            this.context = context;
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

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;
                            CHClass subject;

                            SubjectFullFormList = new ArrayList<CHClass>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                subject = new CHClass();

                                jsonObject = jsonArray.getJSONObject(i);

                                subject.Claim_ID = jsonObject.getString("Claim_ID");

                                subject.Vehicle_ID = jsonObject.getString("Vehicle_ID");

                                subject.Customer_ID = jsonObject.getString("Customer_ID");

                                subject.Claim_Status = jsonObject.getString("Claim_Status");

                                subject.Amount_Assigned = jsonObject.getString("Amount_Assigned");

                                subject.Amount_Payed = jsonObject.getString("Amount_Payed");

                                subject.Transaction_ID = jsonObject.getString("Transaction_ID");

                                subject.Claim_Details_ID = jsonObject.getString("Claim_Details_ID");

                                SubjectFullFormList.add(subject);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            progressBar.setVisibility(View.GONE);

            SubjectFullFormListView.setVisibility(View.VISIBLE);

            if (SubjectFullFormList != null) {

                CHAdapter adapter = new CHAdapter(SubjectFullFormList, context);

                SubjectFullFormListView.setAdapter(adapter);
            }
        }
    }

}
