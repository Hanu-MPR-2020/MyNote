package com.mpr.mynote.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.mpr.mynote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    //attribute
    private static NoteManager instance;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private static final String SELECT_ALL_STATEMENT ="SELECT * FROM " + DbSchema.NoteTable.NAME;

    private static final String INSERT_STM = "INSERT INTO " + DbSchema.NoteTable.NAME  +"(" + DbSchema.NoteTable.Cols.TEXT + ") " +
                                            " VALUES (?)";


    public static NoteManager getInstance(Context context){
        if(instance == null){ //init instance of NoteManager
            instance = new NoteManager(context);
        }
        return instance;
    }

    private NoteManager(Context context){
        this.dbHelper = new DbHelper(context);
        this.db = dbHelper.getWritableDatabase(); //Trigger onCreate/onUpdate
    }

    //all
    public List<Note> all(){
        String sql = "SELECT * FROM " + DbSchema.NoteTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);
        return cursorWrapper.getAllNote();
    }

    public Note findById(long id ){
        String sql =  "SELECT * FROM " + DbSchema.NoteTable.NAME + " WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id +""});

        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);


        return cursorWrapper.getNoteById();
    }

    //add
    public boolean add(Note note){
        SQLiteStatement statement =db.compileStatement(INSERT_STM);

        statement.bindString(1, note.getText());

        //auto generated id
        long id = statement.executeInsert();

        if(id > 0){
            note.setId(id);
            return true;
        }else{
            return false;
        }
    }

    //delete
    public  boolean delete(long id){
        int result = db.delete(DbSchema.NoteTable.NAME, "id = ?", new String[]{id +""});
        return result>0;
    }

    //update
    public boolean update(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("text", note.getText());
        int result = db.update(DbSchema.NoteTable.NAME, contentValues, "id = ?", new String[]{note.getId()+""});
        return result >0;

    }
}
