package com.example.myproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myproject.activities.FirstAidActivity;
import com.example.myproject.activities.MapsActivity;
import com.example.myproject.authentication.AuthActivity;
import com.google.android.gms.maps.MapView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
//תפריט ראשי עם כל הכפתורים לשירותי החירום
//כולל ספינרים ואפשרויות להתקשרות לכל אחד משירותי החירום או איש קשר שהמשתמש בחר

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseAuth firebaseAuth;
    Button button;
    MediaPlayer mp;
    Spinner peopleSpinner;
    String p = "אמא";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.police);

        firebaseAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.SignOut);
        final ImageButton madaButton = findViewById(R.id.madaButton);
        final ImageButton policeButton = findViewById(R.id.policeButton);
        final ImageButton macbiButton = findViewById(R.id.mcbiButton);
        final ImageButton yedidimButton = findViewById(R.id.yedidim);
        final ImageButton pepoleButton = findViewById(R.id.peopleButton);
        final Button first_aid = findViewById(R.id.first_aid);
        MapView map = findViewById(R.id.map);
        GifImageView gif = findViewById(R.id.imageView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
        //של המפות
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity3 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(goToNextActivity3);
                finish();
            }
        });
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, FirstAidActivity.class));
            }
        });
        //כפרותים של החירום שבו מתקשרים לחירום
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

                setCallPeople(p);
            }
        });
        madaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setThePhoneCallMada();
            }
        });

        //כל הספינר של מדא
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

        final Spinner policeSpinner = findViewById(R.id.policeSpinner);
        final List<String> police = new ArrayList<>();
        police.add("תאונה");
        police.add("חטיפה");
        police.add("גניבה");
        police.add("אלימות");
        police.add("אחר");

        final ArrayAdapter<String> eventAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, police);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        policeSpinner.setAdapter(eventAdapter2);
        policeSpinner.setSelection(0, false);

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


        peopleSpinner = findViewById(R.id.peopleSpinner);
        ArrayAdapter<CharSequence> eventAdapter5 = ArrayAdapter.createFromResource(this, R.array.people, android.R.layout.simple_spinner_item);
        eventAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        peopleSpinner.setAdapter(eventAdapter5);
        peopleSpinner.setSelection(0, false);
        peopleSpinner.setOnItemSelectedListener(this);

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        p = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), p, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> parent) { }
    //פונקציה להתקשרות לאנשי קשר על פי בחירה
    public void setCallPeople(String p) {
        if(p.equals("אמא")){
            final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0500000000", null));
            try {
                startActivity(phoneCall);
                finish();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        if(p.equals("אבא")){
            final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0500000001", null));
            try {
                startActivity(phoneCall);
                finish();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        if(p.equals("אח/אחות")){
            final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0500000002", null));
            try {
                startActivity(phoneCall);
                finish();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        if(p.equals("אחר")){
            final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0500000003", null));
            try {
                startActivity(phoneCall);
                finish();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }



    }
    private void setThePhoneCallPolice() {
        playSiren();
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
    //פונקציה להתקשרות למדא
    public void setThePhoneCallMada() {
        final Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "101", null));
        try {
            startActivity(phoneCall);
            finish();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }
    //פונקציה להפעלת הסירנה
    public void playSiren(){
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.police);
            }
            mp.start();
        }
        catch(Exception e) { e.printStackTrace(); }
    }


}