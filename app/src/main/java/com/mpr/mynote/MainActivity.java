package com.mpr.mynote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mpr.mynote.adapter.NoteAdapter;
import com.mpr.mynote.db.NoteManager;
import com.mpr.mynote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNote;
    private List<Note> notes;
    private NoteAdapter noteAdapter;
    private NoteManager noteManager;
    private static final int ADDED_CODE = 1;
    private static final int EDDITED_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        notes = new ArrayList<>();
//        notes.add(new Note("HuuBang"));


        rvNote =findViewById(R.id.rvNote);

        noteManager = NoteManager.getInstance(MainActivity.this);

        notes = noteManager.all();

        noteAdapter = new NoteAdapter(notes);

        rvNote.setAdapter(noteAdapter);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.item_add_new_note:
                Intent intent =  new Intent(this, AddNoteActivity.class);
                startActivityForResult(intent, ADDED_CODE);
                break;

        }

        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "" + requestCode    , Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK &&(requestCode == EDDITED_CODE || requestCode ==ADDED_CODE)){
            notes.clear();
            notes.addAll(noteManager.all());
            noteAdapter.notifyDataSetChanged();
        }
    }

}