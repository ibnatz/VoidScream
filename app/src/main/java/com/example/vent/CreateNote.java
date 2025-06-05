package com.example.vent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNote extends AppCompatActivity {

    private DatabaseReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_note);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
        notesRef = database.getReference("notes");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });


        EditText noteEditText = findViewById(R.id.noteedittext);
        Button createBTN = findViewById(R.id.createBTN);

        // Set button click listener to save the note
        createBTN.setOnClickListener(v -> {
            String note = noteEditText.getText().toString();
            if (!note.isEmpty()) {
                saveNoteToFirebase(note);
            } else {
                Toast.makeText(CreateNote.this, "Please enter a note before saving.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveNoteToFirebase(String note) {

        DatabaseReference newNoteRef = notesRef.push();
        newNoteRef.setValue(note)
                .addOnSuccessListener(aVoid -> Toast.makeText(CreateNote.this, "Note saved successfully.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(CreateNote.this, "Failed to save note.", Toast.LENGTH_SHORT).show());
    }
}
