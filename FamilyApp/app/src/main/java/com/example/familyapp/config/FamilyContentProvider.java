package com.example.familyapp.config;

import android.content.ContentProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

public class FamilyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.familyapp.provider";
    public static final Uri CONTENT_URI_MEMBERS = Uri.parse("content://" + AUTHORITY + "/family_members");
    public static final Uri CONTENT_URI_INTERACTIONS = Uri.parse("content://" + AUTHORITY + "/interactions");

    private static final int FAMILY_MEMBERS = 1;
    private static final int INTERACTIONS = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "family_members", FAMILY_MEMBERS);
        uriMatcher.addURI(AUTHORITY, "interactions", INTERACTIONS);
    }

    private AppDatabase db;

    @Override
    public boolean onCreate() {
        db = AppDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case FAMILY_MEMBERS:
                return db.familyMemberDao().rawQuery("SELECT * FROM family_members", null);
            case INTERACTIONS:
                return db.interactionDao().rawQuery("SELECT * FROM interactions", null);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    // Implement insert/update/delete if needed (tùy luồng sử dụng ContentProvider)
}
