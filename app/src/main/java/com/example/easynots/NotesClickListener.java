package com.example.easynots;

import androidx.cardview.widget.CardView;

import com.example.easynots.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);

}
