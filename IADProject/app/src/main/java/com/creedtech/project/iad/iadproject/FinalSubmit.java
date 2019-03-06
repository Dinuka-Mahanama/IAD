package com.creedtech.project.iad.iadproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalSubmit extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_submit);
        button=(Button)findViewById(R.id.proceed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Mainactivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), VehicleCondition.class);
        startActivity(intent);
        finish();
    }
}
