package com.creedtech.project.iad.iadproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class deploy_P1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button deploybuttononpageone = (Button) findViewById(R.id.ViewDroneLocationOne);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deploy_p1);

    }

    public void buttonclickpageone(View view) {
        Intent intent = new Intent(getApplicationContext(), DeployMap.class);
        startActivity(intent);
        finish();
    }

    @Override
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
}