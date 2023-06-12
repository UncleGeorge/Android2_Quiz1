package com.example.zhangzhiwangsnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        Note note = (Note) getIntent().getSerializableExtra("note");

        titleEditText.setText(note.getName());
        descriptionEditText.setText(note.getDescription());
    }

    public void onSaveButtonClicked(View view) {
        Note note = (Note) getIntent().getSerializableExtra("note");

        note.setName(titleEditText.getText().toString());
        note.setDescription(descriptionEditText.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("note", note);
        intent.putExtra("index", getIntent().getIntExtra("index", -1));
        setResult(RESULT_OK, intent);
        finish();
    }
}
