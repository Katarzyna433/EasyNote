package com.example.easynots.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynots.Models.Notes;
import com.example.easynots.NotesClickListener;
import com.example.easynots.R;

import java.util.List;


public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);
        holder.textView_notes.setText(list.get(position).getNotes());
        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.white));
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_container);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView notes_container;
    TextView textView_title,textView_notes,textView_date;

    public NotesViewHolder(@NonNull View itemView){
        super(itemView);
        notes_container=itemView.findViewById(R.id.notes_container);
        textView_title=itemView.findViewById(R.id.textView_title);
        textView_notes=itemView.findViewById(R.id.textView_notes);
        textView_date=itemView.findViewById(R.id.textView_date);
    }
}
