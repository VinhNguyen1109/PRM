package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GoodsDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "goods.db";
    public static final int DB_VERSION = 1;  // Giữ nguyên nếu chưa muốn gọi onUpgrade

    public GoodsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng
        db.execSQL("CREATE TABLE products (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "price REAL);");
        Log.e("DEBUG-VINHNC", "onCreate: DB created and inserting default data");

        // Insert dữ liệu mẫu ngay khi tạo DB
        db.execSQL("INSERT INTO products (name, price) VALUES ('KhangPQ', 9.99);");
        db.execSQL("INSERT INTO products (name, price) VALUES ('VinhNC', 19.99);");
        db.execSQL("INSERT INTO products (name, price) VALUES ('HungVQ', 19.99);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DEBUG-VINHNC", "onUpgrade called: old=" + oldVersion + ", new=" + newVersion);

        // Nếu bạn cần nâng cấp sau này thì viết logic tại đây
        // Ví dụ:
        // if (oldVersion < 2) { ... }
    }
}
