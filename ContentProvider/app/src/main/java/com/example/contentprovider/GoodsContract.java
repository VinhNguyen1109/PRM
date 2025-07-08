package com.example.contentprovider;

import android.net.Uri;

public final class GoodsContract {
    public static final String AUTHORITY = "com.example.goodsprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/products");

    public static final String TABLE_NAME = "products";

    public static final String MIME_TYPE_DIR = "vnd.android.cursor.dir/vnd.com.example.products";
    public static final String MIME_TYPE_ITEM = "vnd.android.cursor.item/vnd.com.example.products";

    public static class Columns {
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
    }
}
