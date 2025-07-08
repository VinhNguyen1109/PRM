package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

public class GoodsContentProvider extends ContentProvider {

    private GoodsDBHelper dbHelper;
    private static final int PRODUCTS = 1;
    private static final int PRODUCT_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(GoodsContract.AUTHORITY, "products", PRODUCTS);
        uriMatcher.addURI(GoodsContract.AUTHORITY, "products/#", PRODUCT_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new GoodsDBHelper(getContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("DEBUG-VINHNC", "DB version: " + db.getVersion());

        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                cursor = db.query(GoodsContract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = GoodsContract.Columns._ID + "=?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                cursor = db.query(GoodsContract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(GoodsContract.TABLE_NAME, null, values);
        return Uri.withAppendedPath(GoodsContract.CONTENT_URI, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) { /* ... */ return 0; }
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) { /* ... */ return 0; }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                return GoodsContract.MIME_TYPE_DIR;
            case PRODUCT_ID:
                return GoodsContract.MIME_TYPE_ITEM;
            default:
                return null;
        }
    }
}
