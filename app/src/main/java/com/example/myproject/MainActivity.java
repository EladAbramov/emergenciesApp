package com.example.myproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.activities.FirstAidActivity;
import com.example.myproject.authentication.AuthActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.SignOut);
        findViewById(R.id.madaButton);
        final ImageButton policeButton = findViewById(R.id.policeButton);
        final ImageButton macbiButton = findViewById(R.id.mcbiButton);
        final ImageButton yedidimButton = findViewById(R.id.yedidim);
        final ImageButton pepoleButton = findViewById(R.id.peopleButton);
        final Button first_aid = findViewById(R.id.first_aid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });

        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, FirstAidActivity.class));
            }
        });

        yedidimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setTheCallYedidim();

            }
        });
        macbiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setTheCallMacbi();
            }
        });
        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setThePhoneCallPolice();
            }
        });
        pepoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setCallPeople();
            }
        });

        final Spinner spiner = (Spinner) findViewById(R.id.madaSpinner);
        final List<String> mada = new ArrayList<>();
        mada.add("התקף לב");
        mada.add("תאונה");
        mada.add("לחץ דם");
        mada.add("שבץ מוחי");
        mada.add("דימום");
        mada.add("כויות");
        mada.add("התקף אסטמה");
        mada.add("לידה");
        mada.add("אחר");

        final ArrayAdapter<String> eventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mada);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(eventAdapter);
        spiner.setSelection(0, false);

        final Spinner policespinner = findViewById(R.id.policeSpinner);
        final List<String> police = new ArrayList<>();
        police.add("תאונה");
        police.add("חטיפה");
        police.add("גניבה");
        police.add("אלימות");
        police.add("אחר");

        final ArrayAdapter<String> eventAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, police);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        policespinner.setAdapter(eventAdapter2);
        policespinner.setSelection(0, false);

        final Spinner macbispinner = findViewById(R.id.mcbiSpinner);
        final List<String> macbi = new ArrayList<>();
        macbi.add("שריפה");
        macbi.add("בעל חיים תקוע");
        macbi.add("חילוץ");
        macbi.add("אחר");

        final ArrayAdapter<String> eventAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, macbi);
        eventAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        macbispinner.setAdapter(eventAdapter3);
        macbispinner.setSelection(0, false);

        final Spinner yedidimspinner = findViewById(R.id.yedidimSpinner);
        final List<String> yedidim = new ArrayList<>();
        yedidim.add("החלפת גלגל");
        yedidim.add("התנעת רכב");
        yedidim.add("חילוץ");
        yedidim.add("רכב תקוע");
        yedidim.add("אחר");
        final ArrayAdapter<String> eventAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yedidim);
        eventAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yedidimspinner.setAdapter(eventAdapter4);
        yedidimspinner.setSelection(0, false);

        final Spinner spinner = findViewById(R.id.peopleSpinner);
        final List<String> people = new ArrayList<>();
        people.add("אמא");
        people.add("אבא");
        people.add("אח/אחות");
        people.add("אחר");
        final ArrayAdapter<String> eventAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, people);
        eventAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(eventAdapter5);
        spinner.setSelection(0, false);
    }

    private void setThePhoneCallPolice() {
        final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "100", null));
        try {
            startActivity(phoneCall);
            finish();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void setTheCallMacbi() {
        final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "102", null));
        try {
            startActivity(phoneCall);
            finish();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void setTheCallYedidim() {
        final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "053-3131310", null));
        try {
            startActivity(phoneCall);
            finish();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final Intent textMessage = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:053-3131310"));
        textMessage.putExtra("sms_body", "מקרה חירום ");
        try {
            startActivity(textMessage);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void setCallPeople() {
        final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "", null));
        try {
            startActivity(phoneCall);
            finish();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        final Intent textMessage = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:0500000000"));
        textMessage.putExtra("sms_body", "מקרה חירום ");
        try {
            startActivity(textMessage);
        } catch (final Exception ignored) {

        }

    }
}