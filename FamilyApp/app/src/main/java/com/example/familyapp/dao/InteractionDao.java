package com.example.familyapp.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.familyapp.model.InteractionEntity;

import java.util.List;

@Dao
public interface InteractionDao {
    @Insert
    long insert(InteractionEntity interaction);

    @Query("SELECT * FROM interactions WHERE familyMemberId = :id ORDER BY date DESC")
    LiveData<List<InteractionEntity>> getByFamilyMember(long id);

    Cursor rawQuery(String query, Object o);
}
