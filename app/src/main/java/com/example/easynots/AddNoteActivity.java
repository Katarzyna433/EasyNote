package com.example.easynots;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easynots.Models.Notes;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    Notes notes;
    boolean isOldNote = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);
        MaterialButton cancelbtn = findViewById(R.id.cancelbtn);


        notes = new Notes();
        try{
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            titleInput.setText(notes.getTitle());
            descriptionInput.setText(notes.getNotes());
            isOldNote=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=titleInput.getText().toString();
                String description=descriptionInput.getText().toString();
                if(description.isEmpty())
                {
                    Toast.makeText(AddNoteActivity.this,"Please add some notes ",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter=new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date=new Date();
                if(!isOldNote){
                    notes=new Notes();
                }
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));
                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }
}