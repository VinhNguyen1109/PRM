package com.example.familyapp.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.familyapp.dao.FamilyMemberDao;
import com.example.familyapp.dao.InteractionDao;
import com.example.familyapp.model.FamilyMemberEntity;
import com.example.familyapp.model.InteractionEntity;

@Database(entities = {FamilyMemberEntity.class, InteractionEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FamilyMemberDao familyMemberDao();
    public abstract InteractionDao interactionDao();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "family.db")
                .fallbackToDestructiveMigration()
                .build();
    }
}
