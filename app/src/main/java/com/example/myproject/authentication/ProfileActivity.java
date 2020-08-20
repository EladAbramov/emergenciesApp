package com.example.myproject.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    public static String nameOfCollection = "user";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button button = findViewById(R.id.sent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
                EditText id = findViewById(R.id.id);
                EditText name = findViewById(R.id.name);
                EditText lastName = findViewById(R.id.LastName);
                EditText health = findViewById(R.id.Health);
                EditText gender = findViewById(R.id.Gender);
                EditText date = findViewById(R.id.date);

                String phone = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber();
                User user = new User(id.getText().toString(), name.getText().toString(), lastName.getText().toString(), health.getText().toString(), gender.getText().toString(), date.getText().toString());

                if (phone != null) {
                    db.collection(nameOfCollection).document(phone).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ProfileActivity.this,"עודכן בהצלחה",Toast.LENGTH_SHORT).show();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("", "error writing document", e);
                        }
                    });
                }
            }
        });
        SharedPreferences sp = getSharedPreferences("myproject", MODE_PRIVATE);
        final String phoneNumber = sp.getString("phone_number", null);

        if (phoneNumber != null) {
            db.collection(ProfileActivity.nameOfCollection).document(phoneNumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document != null) {
                            if (document.exists()) {
                                Log.d("", "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d("", "No such document");
                            }
                        }
                    } else {
                        Log.d("", "get failed with ", task.getException());
                    }
                }
            });
        }
    }

}
