package com.example.familyapp.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.familyapp.model.FamilyMemberEntity;

import java.util.List;

@Dao
public interface FamilyMemberDao {
    @Insert
    long insert(FamilyMemberEntity member);

    @Update
    void update(FamilyMemberEntity member);

    @Delete
    void delete(FamilyMemberEntity member);

    @Query("SELECT * FROM family_members ORDER BY lastInteractionTime DESC")
    LiveData<List<FamilyMemberEntity>> getAll();

    Cursor rawQuery(String query, Object o);
}
