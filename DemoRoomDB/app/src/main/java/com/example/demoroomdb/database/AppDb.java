package com.example.demoroomdb.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.demoroomdb.dao.ProductDao;
import com.example.demoroomdb.model.Product;

@Database(entities = {Product.class}, version = 1)

public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;
    public abstract ProductDao productDao();

    public static AppDb getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDb.class,
                            "goods.db"
                    ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
