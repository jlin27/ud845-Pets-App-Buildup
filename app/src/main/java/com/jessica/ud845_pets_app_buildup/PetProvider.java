package com.jessica.ud845_pets_app_buildup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by jessicalin on 6/9/16.
 */
public class PetProvider extends ContentProvider {

    public static final String CONTENT_ITEM_TYPE = "New Pet";
    private static final String AUTHORITY = "com.udacity.beginningandroid.pets.app";
    private static final String BASE_PATH = "activities";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int PETS = 1;
    private static final int PET_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // static initializer ... this is run the first time anything is called from this class
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, PETS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", PET_ID);

    }

    private SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        PetDbHelper dbHelper = new PetDbHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        // TODO Handle the case when we only want a specific row to edit
        if(uriMatcher.match(uri) == PET_ID) {
            selection = PetContract.PetEntry._ID + "=" + uri.getLastPathSegment();
        }

        return mDatabase.query(PetContract.PetEntry.TABLE_NAME, PetContract.PetEntry.TABLE_COLUMNS, selection, null, null,
                null, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mDatabase.insert(PetContract.PetEntry.TABLE_NAME, null, values);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDatabase.delete(PetContract.PetEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mDatabase.update(PetContract.PetEntry.TABLE_NAME, values, selection, selectionArgs);
    }
}
