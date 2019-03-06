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

public class Client extends AppCompatActivity {

    String ServerURL = "https://creedtech.org/iadproject/signup/client/client.php";
    EditText fname, lname, address, contactno, password, email, age, NID, DRID, OCCPU;
    Button button;
    String TempFname, TempLname, TempAddress, TempContact, TempPass, TempEmail, TempAge, TempNID, TempDRID, TempOCCPU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        address = (EditText) findViewById(R.id.address);
        contactno = (EditText) findViewById(R.id.contactno);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        NID = (EditText) findViewById(R.id.NIC);
        DRID = (EditText) findViewById(R.id.DriveID);
        OCCPU = (EditText) findViewById(R.id.occupation);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();
                if (checkEmpty()) {
                    InsertData(TempFname, TempLname, TempAddress, TempContact, TempPass, TempEmail, TempAge, TempNID, TempDRID, TempOCCPU);

                    Thread myThread = new Thread() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(getApplicationContext(), Vehicle.class);
                            startActivity(intent);
                            finish();
                        }
                    };
                    myThread.start();
                } else {
                    Toast.makeText(Client.this, "Please Ensure All Fields Are Filled Properly", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void GetData() {

        TempFname = fname.getText().toString();
        TempLname = lname.getText().toString();
        TempAddress = address.getText().toString();
        TempContact = contactno.getText().toString();
        TempPass = password.getText().toString();
        TempEmail = email.getText().toString();
        TempAge = age.getText().toString();
        TempNID = NID.getText().toString();
        TempDRID = DRID.getText().toString();
        TempOCCPU = OCCPU.getText().toString();

    }

    public void InsertData(final String fname, final String lname, final String address, final String contactno, final String password,
                           final String email, final String age, final String nid, final String drid, final String occ) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String FNameHolder = fname;
                String LNameHolder = lname;
                String AddressHolder = address;
                String ContactHolder = contactno;
                String PassHolder = password;
                String EmailHolder = email;
                String AgeHolder = age;
                String NidHolder = nid;
                String DridHolder = drid;
                String OccupHolder = occ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("fname", FNameHolder));
                nameValuePairs.add(new BasicNameValuePair("lname", LNameHolder));
                nameValuePairs.add(new BasicNameValuePair("address", AddressHolder));
                nameValuePairs.add(new BasicNameValuePair("contactno", ContactHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PassHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("age", AgeHolder));
                nameValuePairs.add(new BasicNameValuePair("nid", NidHolder));
                nameValuePairs.add(new BasicNameValuePair("drid", DridHolder));
                nameValuePairs.add(new BasicNameValuePair("occ", OccupHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(Client.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(fname, lname, address, contactno, password, email, age, nid, drid, occ);
    }

    public Boolean checkEmpty() {
        if(isNumberValid(TempAge) && isNumberValid(TempContact) && isTextvalid(TempAddress) && isTextvalid(TempDRID) && isTextvalid(TempFname)
                && isTextvalid(TempLname) && isTextvalid(TempNID) && isTextvalid(TempOCCPU) && isValidEmail(TempEmail) && isValidPassword(TempPass)){
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

    //Validating Email
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    //Validating Numbers
    private boolean isNumberValid(String no) {
        if (no.matches("[0-9]+") && no != null) {
            return true;
        }
        return false;
    }
}