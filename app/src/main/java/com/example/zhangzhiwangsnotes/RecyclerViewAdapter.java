package com.example.zhangzhiwangsnotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Note> notes;
    private NoteEventListener listener;

//    public RecyclerViewAdapter(Context context) {
//        this.context = context;
//    }

    public RecyclerViewAdapter(Context context, List<Note> notes, NoteEventListener listener) {
        this.context = context;
        this.notes = notes;
        this.listener = listener;
    }

    // onCreateViewHolder() method
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent,false);
        return new ViewHolder(view);
    }

    // onBindViewHolder() method
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
//        holder.title.setText("Note " + (position + 1));
//        holder.description.setText("Description " + (position + 1));
//        holder.date.setText("Jun 7, 2023");
//    }

    // getItemCount() method
//    @Override
//    public int getItemCount() {
//        return 6;
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getName());
        holder.description.setText(note.getDescription());
        holder.date.setText(new SimpleDateFormat("MM dd, yyyy ").format(note.getDate()));
        holder.priority.setChecked(note.isPriority());

        holder.itemView.setOnClickListener(v -> listener.onNoteClick(note));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onNoteLongClick(note);
            return true;
        });
        holder.priority.setOnCheckedChangeListener((buttonView, isChecked) -> {
            note.setPriority(isChecked);
            Toast.makeText(context, "Note " + (position + 1) + " is " + (isChecked ? "high" : "low") + " priority", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface NoteEventListener {
        void onNoteClick(Note note);
        void onNoteLongClick(Note note);

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // initialize profileLetter,name,number
        TextView title, description, date;
        CheckBox priority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
