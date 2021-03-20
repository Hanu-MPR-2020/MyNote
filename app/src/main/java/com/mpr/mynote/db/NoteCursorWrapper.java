package com.mpr.mynote.db;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.mpr.mynote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteCursorWrapper extends CursorWrapper {

    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        long id = getLong(getColumnIndex("id"));
        String text = getString(getColumnIndex(DbSchema.NoteTable.Cols.TEXT));

        Note note = new Note(id, text);
        return note;
    }

    public Note getNoteById(){
        Note note = null;

        moveToFirst();
        if (!isAfterLast()) {
            note = getNote();
        }
        return note;
    }


    public List<Note> getAllNote(){
        List<Note> notes = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()){
            Note note = getNote();
            notes.add(note);

            moveToNext();
        }
        return notes;
    }
}
