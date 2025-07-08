package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbcontext extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Demo_DB";

    private static final String TB_DICT = "dictionary";
    private static final int DB_VERSION = 1;

    private static final String TB_DICT_ID = "id";

    private static final String TB_DICT_WORD = "word";

    private static final String TB_DICT_DEFINATION = "dictionary";


    public Dbcontext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS dictionary (\n" +
                "                                          id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                                          word TEXT NOT NULL,\n" +
                "                                          definition TEXT\n" +
                "                                      );\n";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upgradeStep(oldVersion, newVersion);
    }

    private void upgradeStep(int current, int target) {
        if (current >= target) return;
        int next = current + 1;
        System.out.println("⬆️ Update from version " + current + " to version " + next);
        if (current == 1) {
            System.out.println(" update from 1 to 2");
        } else if (current == 2) {
            System.out.println("update form 2 ro 3");
        } else if (current == 3) {
            System.out.println("update from 3 - 4");
        } else {
            System.out.println("No have  " + current);
        }
        upgradeStep(next, target);
    }



    public void insertNewWord(String w, String d) {
        String sql = " INSERT INTO dictionary (word, definition) VALUES (?, ?)";
        getWritableDatabase().execSQL(sql, new String[]{w, d});
    }

    public Long insert2(String w, String d) {
        ContentValues values = new ContentValues();
        values.put(TB_DICT_WORD, w);
        values.put(TB_DICT_DEFINATION, d);
        return getWritableDatabase().insert(TB_DICT, null, values);
    }

    public void deleteWord(int id) {
        getWritableDatabase().delete(TB_DICT, TB_DICT_ID + "= ?", new String[]{String.valueOf(id)});
    }

    public Cursor getAllWord() {
        String sql = " Select * from dictionary";
        return getWritableDatabase().rawQuery(sql, null);
    }

}
