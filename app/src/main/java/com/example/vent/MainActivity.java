package com.example.vent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.homescreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        try {
//            FirebaseApp.initializeApp(this);
//            FirebaseDatabase.getInstance().getReference().child("test").setValue("Connection Test");
//            Log.d("FirebaseConnection", "Firebase is connected successfully");
//        } catch (Exception e) {
//            Log.e("FirebaseConnection", "Failed to connect to Firebase", e);
//        }

        Button openAdd = findViewById(R.id.openAdd);
        openAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateNote.class);
            startActivity(intent);
        });

        Button showNotesBtn = findViewById(R.id.showNotesBtn);
        showNotesBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShowNotes.class);
            startActivity(intent);
        });

        Button editNotesbtn = findViewById(R.id.editNotesbtn);
        editNotesbtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditNotes.class);
            startActivity(intent);
        });

        Button deleteNotesBtn = findViewById(R.id.deleteNotesBtn);
        deleteNotesBtn.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
            notesRef = database.getReference("notes");

            notesRef.removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(MainActivity.this, "Notes deleted successfully.", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e ->{
                       Toast.makeText(MainActivity.this, "Failed to delete notes.", Toast.LENGTH_SHORT).show();
                    });

        });



    }
}