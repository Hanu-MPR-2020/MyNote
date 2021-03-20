package com.mpr.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.mpr.mynote.db.NoteManager;
import com.mpr.mynote.model.Note;

public class Edit_Note extends AppCompatActivity {
    private EditText edtNote;
    private Note note;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__note);

        Intent intent = getIntent();
        id = intent.getLongExtra("id", -1);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        NoteManager noteManager = NoteManager.getInstance(this);
        note = noteManager.findById(id);

        edtNote = findViewById(R.id.edt_text_note);
        edtNote.setText(note.getText());
    }

    @Override
    public void onBackPressed() {
        note.setText(edtNote.getText().toString());
        note.setId(id);
        NoteManager.getInstance(this).update(note);
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}