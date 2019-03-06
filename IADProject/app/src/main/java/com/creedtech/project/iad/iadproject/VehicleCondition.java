package com.creedtech.project.iad.iadproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleCondition extends AppCompatActivity {

    Button proceed;
    String VCondition,PCondition,TCondition,AIRCondition,RCondition,RIMCondition,CCondition,wCondition;
    String ServerURL="https://creedtech.org/iadproject/signup/condition/vehiclecondition.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_condition2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner1list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner1list, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.spinner1list, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);

        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);

      /*  public new View.OnClickListener(){
            @Override
            public void onClick(View view){

                GetData();

                InsertData(VCondition,PCondition,TCondition,AIRCondition,RCondition,RIMCondition,CCondition,wCondition);

                Intent intent=new Intent(getApplicationContext(),FinalSubmit.class);
                startActivity(intent);
                finish();


            }
        });*/
    }

    public void buttonclickprocess(View view) {
        GetData();

        InsertData(VCondition,PCondition,TCondition,AIRCondition,RCondition,RIMCondition,CCondition,wCondition);

        Intent intent=new Intent(getApplicationContext(),FinalSubmit.class);
        startActivity(intent);
        finish();
    }

    public void GetData(){

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        VCondition = spinner.getSelectedItem().toString();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        PCondition = spinner2.getSelectedItem().toString();

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        TCondition = spinner3.getSelectedItem().toString();

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        AIRCondition = spinner4.getSelectedItem().toString();

        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        RCondition = spinner5.getSelectedItem().toString();

        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        RIMCondition = spinner6.getSelectedItem().toString();

        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        CCondition = spinner7.getSelectedItem().toString();

        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        wCondition = spinner8.getSelectedItem().toString();

        Log.d("myTag", VCondition+"/"+PCondition+"/"+TCondition+"/"+AIRCondition
                +"/"+RCondition+"/"+RIMCondition+"/"+CCondition+"/"+wCondition);
    }

    public void InsertData(final String VCondi,final String PCondi,final String TCondi,final String AIRCondi
            ,final String RCondi,final String RIMCondi,final String CCondi,final String wCondi){

        class SendPostReqAsyncTask extends AsyncTask<String,Void,String> {
            @Override
            protected String doInBackground(String...params){

                String VCondit=VCondi;
                String PCondit=PCondi;
                String TCondit=TCondi;
                String AIRCondit=AIRCondi;
                String RCondit=RCondi;
                String RIMCondit=RIMCondi;
                String CCondit=CCondi;
                String wCondit=wCondi;

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("VCondi",VCondit));
                nameValuePairs.add(new BasicNameValuePair("PCondi",PCondit));
                nameValuePairs.add(new BasicNameValuePair("TCondi",TCondit));
                nameValuePairs.add(new BasicNameValuePair("AIRCondi",AIRCondit));
                nameValuePairs.add(new BasicNameValuePair("RCondi",RCondit));
                nameValuePairs.add(new BasicNameValuePair("RIMCondi",RIMCondit));
                nameValuePairs.add(new BasicNameValuePair("CCondi",CCondit));
                nameValuePairs.add(new BasicNameValuePair("wCondi",wCondit));


                try{
                    HttpClient httpClient=new DefaultHttpClient();

                    HttpPost httpPost=new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse=httpClient.execute(httpPost);

                    HttpEntity httpEntity=httpResponse.getEntity();


                }catch(ClientProtocolException e){

                }catch(IOException e){

                }
                return"DataInsertedSuccessfully";
            }

            @Override
            protected void onPostExecute(String result){

                super.onPostExecute(result);

                Toast.makeText(VehicleCondition.this,"Vehicle Data Submitted Successfully",Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask=new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(VCondi,PCondi,TCondi,AIRCondi,RCondi,RIMCondi,CCondi,wCondi);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Vehicle.class);
        startActivity(intent);
        finish();
    }
}
