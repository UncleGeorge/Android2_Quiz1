package com.example.zhangzhiwangsnotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.NoteEventListener {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        notes = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String name = "Note " + i;
            String description = i == 2 ? "ZhangzhiWang-N01371888" : "Description " + i;
            boolean priority = false;
            Date date = new Date();
            notes.add(new Note(name, description, priority, date));
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new RecyclerViewAdapter(this, notes, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("note", note);
        intent.putExtra("index", notes.indexOf(note));
        startActivityForResult(intent, 1);
    }

    @Override
    public void onNoteLongClick(Note note) {

        new AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note？")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notes.remove(note);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Note note = (Note) data.getSerializableExtra("note");
            int index = data.getIntExtra("index", -1);
            if (index != -1) {
                notes.set(index, note);
                adapter.notifyDataSetChanged();
            } else {
                // Error handling: index not found
            }
        }
    }
}