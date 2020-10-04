package com.example.myproject.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    public EditText editTextMobile;
    Button btnContinue;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        //initialize fields
        editTextMobile = findViewById(R.id.editTextMobile);
        btnContinue = findViewById(R.id.buttonContinue);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //check whether the user is logged in
        if (currentUser != null) {
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mobileNo = editTextMobile.getText().toString().trim();
                    if (mobileNo.isEmpty() || mobileNo.length() < 12) {
                        editTextMobile.setError("Enter auth_screen_background valid mobile");
                        editTextMobile.requestFocus();
                        return;
                    }
                    Intent intent = new Intent(AuthActivity.this, VerifyPhoneActivity.class);
                    intent.putExtra("mobile", mobileNo);
                    startActivity(intent);

                }
            });

        }
    }

}