package com.mpr.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.mpr.mynote.db.NoteManager;
import com.mpr.mynote.model.Note;

public class AddNoteActivity extends AppCompatActivity {
    private EditText edt_add_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edt_add_note = findViewById(R.id.edt_add_note);
    }

    @Override
    public void onBackPressed() {
        String text = edt_add_note.getText().toString();
        Note note = new Note(text);
        NoteManager.getInstance(this).add(note);

        setResult(this.RESULT_OK);
        super.onBackPressed();
    }
}