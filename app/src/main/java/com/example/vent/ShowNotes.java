package com.example.vent;

import android.os.Bundle;
import android.util.Log;
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

public class ShowNotes extends AppCompatActivity {
    private DatabaseReference notesRef;
    private TextView showNotesTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notesapp-c862a-default-rtdb.firebaseio.com/");
        notesRef = database.getReference("notes");
        showNotesTxt = findViewById(R.id.showNotesTxt);
        readNotes();

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
                showNotesTxt.setText(notesText.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "onCancelled: Error");
            }
        });
    }
}