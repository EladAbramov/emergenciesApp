package com.example.myproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myproject.R;

public class FirstAidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__aid);
        Button stoke=findViewById(R.id.stroke);
        Button burn=findViewById(R.id.burn);
        Button asthma=findViewById(R.id.asthma);
        Button bleed=findViewById(R.id.bleed);
        Button hyperthermia=findViewById(R.id.hyperthrmia);
        Button resucitation=findViewById(R.id.resucitation);

        stoke.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, StrokeActivity.class));
            }
        });
        burn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, BurnActivity.class));
            }
        });
        asthma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, AsthmaActivity.class));
            }
        });
        bleed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, BleedActivity.class));
            }
        });
        hyperthermia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, Hyperthermia_Activity.class));
            }
        }) ;
        resucitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstAidActivity.this, ResuscitationActivity.class));
            }
        });
    }
}
