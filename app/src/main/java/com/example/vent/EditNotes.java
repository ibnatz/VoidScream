/**package com.example.vent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNotes extends AppCompatActivity {
    private DatabaseReference notesRef;
    private EditText editTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
        notesRef = database.getReference("notes");
        editTextBox = findViewById(R.id.editTextBox);
        readNotes();
        Button updateBTN = findViewById(R.id.updateBTN);

        updateBTN.setOnClickListener(v -> {
            updateNotes();
        });


    }


    private  void readNotes(){
        notesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder notesText = new StringBuilder();
                for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                    String note = noteSnapshot.getValue(String.class);
                    notesText.append(note).append("\n");
                }
                editTextBox.setText(notesText.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "onCancelled: Error");
            }
        });
    }


    private  void updateNotes(){
        String updatedNotes = editTextBox.getText().toString();


        notesRef.removeValue()
                .addOnSuccessListener(aVoid -> {

                }).addOnFailureListener(e ->{

                });

        DatabaseReference newNoteRef = notesRef.push();
        newNoteRef.setValue(updatedNotes)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Note updated successfully.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to update note.", Toast.LENGTH_SHORT).show());


    }
} */


/**package com.example.vent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNotes extends AppCompatActivity {
    private DatabaseReference notesRef;
    private EditText editTextBox;
    private String noteKeyToEdit = null; // Store the key of the note to edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_notes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
        notesRef = database.getReference("notes");

        editTextBox = findViewById(R.id.editTextBox);
        Button updateBTN = findViewById(R.id.updateBTN);

        readFirstNote(); // Load note to edit

        updateBTN.setOnClickListener(v -> updateNote());
    }

    private void readFirstNote() {
        notesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                    String note = noteSnapshot.getValue(String.class);
                    noteKeyToEdit = noteSnapshot.getKey(); // store key
                    editTextBox.setText(note); // populate EditText
                    break; // only get first note
                }

                if (noteKeyToEdit == null) {
                    Toast.makeText(EditNotes.this, "No notes to edit.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read note", error.toException());
            }
        });
    }

    private void updateNote() {
        String updatedNote = editTextBox.getText().toString().trim();
        if (noteKeyToEdit != null && !updatedNote.isEmpty()) {
            notesRef.child(noteKeyToEdit).setValue(updatedNote)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditNotes.this, "Note updated successfully.", Toast.LENGTH_SHORT).show();
                        editTextBox.setText("");
                        noteKeyToEdit = null;
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditNotes.this, "Failed to update note.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(EditNotes.this, "No note selected or empty content.", Toast.LENGTH_SHORT).show();
        }
    }
} */


package com.example.vent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNotes extends AppCompatActivity {
    private DatabaseReference notesRef;
    private EditText editTextBox;
    private String noteKeyToEdit = null; // Store the key of the note to edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_notes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
        notesRef = database.getReference("notes");

        editTextBox = findViewById(R.id.editTextBox);
        Button updateBTN = findViewById(R.id.updateBTN);

        readFirstNote(); // Load note to edit

        updateBTN.setOnClickListener(v -> updateNote());
    }

    private void readFirstNote() {
        notesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                    String note = noteSnapshot.getValue(String.class);
                    noteKeyToEdit = noteSnapshot.getKey(); // store key
                    editTextBox.setText(note); // populate EditText
                    break; // only get first note
                }

                if (noteKeyToEdit == null) {
                    Toast.makeText(EditNotes.this, "No notes to edit.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read note", error.toException());
            }
        });
    }

    private void updateNote() {
        String updatedNote = editTextBox.getText().toString().trim();
        if (noteKeyToEdit != null && !updatedNote.isEmpty()) {
            notesRef.child(noteKeyToEdit).setValue(updatedNote)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditNotes.this, "Note updated successfully.", Toast.LENGTH_SHORT).show();
                        editTextBox.setText("");
                        noteKeyToEdit = null;
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditNotes.this, "Failed to update note.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(EditNotes.this, "No note selected or empty content.", Toast.LENGTH_SHORT).show();
        }
    }
}

