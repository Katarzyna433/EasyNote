package com.example.easynots;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easynots.Adapter.NotesListAdapter;
import com.example.easynots.Database.RoomDB;
import com.example.easynots.Models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    List<Notes> notes = new ArrayList<>();
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    RoomDB database;
    FloatingActionButton fab_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        TextView textView=findViewById(R.id.notestitle);
        recyclerView = findViewById(R.id.recyler_home);
        database = RoomDB.getInstance(this);
        fab_add = findViewById(R.id.fab_add);
        notes = database.noteDAO().getAll();
        
        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(NoteActivity.this,AddNoteActivity.class) ;
               startActivityForResult(intent,101);
            }
        });

        String text=getIntent().getStringExtra("key");
        if(text!=null){
            textView.setText(text);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                database.noteDAO().insert(new_notes);
                notes.clear();
                notes.addAll(database.noteDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }else if (requestCode == 102) {
                if (resultCode == Activity.RESULT_OK) {
                    Notes new_notes = (Notes) data.getSerializableExtra("note");
                    database.noteDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());
                    notes.clear();
                    notes.addAll(database.noteDAO().getAll());
                    notesListAdapter.notifyDataSetChanged();
                }
        }
    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter=new NotesListAdapter(NoteActivity.this,notes,notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {
            Intent intent=new Intent(NoteActivity.this,AddNoteActivity.class);
            intent.putExtra("old_note",notes);
            startActivityForResult(intent,102);
        }

        @Override
        public void onLongClick(Notes notesSe, CardView cardView) {
            database.noteDAO().delete(notesSe);
            notes.clear();
            notes.addAll(database.noteDAO().getAll());
            notesListAdapter.notifyDataSetChanged();

            Toast.makeText(NoteActivity.this,"Note Deleted",Toast.LENGTH_SHORT).show();
        }
    };
}