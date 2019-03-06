package com.creedtech.project.iad.iadproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehicle extends AppCompatActivity {

    String ServerURL="https://creedtech.org/iadproject/signup/vehicle/vehicle.php";
    EditText vnum,engnum,chasnum,make,model,year,color,millage;
    Button button;
    String TempVnum,TempEngnum,TempChasnum,TempMake,TempModel,TempYear,TempColor,TempMillage;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        vnum=(EditText)findViewById(R.id.vnum);
        engnum=(EditText)findViewById(R.id.engnum);
        chasnum=(EditText)findViewById(R.id.chasnum);
        make=(EditText)findViewById(R.id.make);
        model=(EditText)findViewById(R.id.model);
        year=(EditText)findViewById(R.id.year);
        color=(EditText)findViewById(R.id.color);
        millage=(EditText)findViewById(R.id.millage);
        button=(Button)findViewById(R.id.proceed);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){

                GetData();
                if(checkEmpty()){
                InsertData(TempVnum,TempEngnum,TempChasnum,TempMake,TempModel,TempYear,TempColor,TempMillage);

                Thread myThread=new Thread(){
                    @Override
                            public void run(){

                        Intent intent=new Intent(getApplicationContext(),VehicleCondition.class);
                        startActivity(intent);
                        finish();
                    }
                };
                myThread.start();}
                else{
                   Toast.makeText(Vehicle.this, "Please Ensure All Fields Are Filled Properly", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void GetData(){

        TempVnum=vnum.getText().toString();
        TempEngnum=engnum.getText().toString();
        TempChasnum=chasnum.getText().toString();
        TempMake=make.getText().toString();
        TempModel=model.getText().toString();
        TempYear=year.getText().toString();
        TempColor=color.getText().toString();
        TempMillage=millage.getText().toString();

    }

    public void InsertData(final String vnum,final String engnum,final String chasnum,final String make,final String model,final String year,
                            final String colo,final String mill){

        class SendPostReqAsyncTask extends AsyncTask<String,Void,String> {
            @Override
                    protected String doInBackground(String...params){

                String VnumHolder=vnum;
                String EngnumHolder=engnum;
                String ChasnumHolder=chasnum;
                String MakeHolder=make;
                String ModelHolder=model;
                String YearHolder=year;
                String ColoHolder=colo;
                String MillHolder=mill;

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("vnum",VnumHolder));
                nameValuePairs.add(new BasicNameValuePair("engnum",EngnumHolder));
                nameValuePairs.add(new BasicNameValuePair("chasnum",ChasnumHolder));
                nameValuePairs.add(new BasicNameValuePair("make",MakeHolder));
                nameValuePairs.add(new BasicNameValuePair("model",ModelHolder));
                nameValuePairs.add(new BasicNameValuePair("year",YearHolder));
                nameValuePairs.add(new BasicNameValuePair("colo",ColoHolder));
                nameValuePairs.add(new BasicNameValuePair("mill",MillHolder));

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

                Toast.makeText(Vehicle.this,"Vehicle Data SubmittedSuccessfully",Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask=new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(vnum,engnum,chasnum,make,model,year,colo,mill);
    }

    public Boolean checkEmpty() {
        if(isTextvalid(TempChasnum) && isTextvalid(TempColor) && isTextvalid(TempEngnum) && isTextvalid(TempMake) && isTextvalid(TempMillage)
                && isTextvalid(TempModel) && isTextvalid(TempVnum) && isTextvalid(TempYear)){
            return true;
        }
        return false;
    }

    //Validating Text
    private boolean isTextvalid(String text) {
        if (text != null && text.length() >= 4) {
            return true;
        }
        return false;
    }
}
