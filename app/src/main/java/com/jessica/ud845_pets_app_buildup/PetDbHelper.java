package com.jessica.ud845_pets_app_buildup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jessicalin on 6/8/16.
 */
public class PetDbHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "pets.db";

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    final private Context mContext;

    public PetDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL Statement to Create the Pets Table
        final String SQL_CREATE_PETS_TABLE =

                "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " (" + PetContract.PetEntry._ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                        + PetContract.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                        + PetContract.PetEntry.COLUMN_PET_AGE + " INTEGER, "
                        + PetContract.PetEntry.COLUMN_PET_BREED + " TEXT )"
                ;

        db.execSQL(SQL_CREATE_PETS_TABLE);
        Log.v(PetDbHelper.class.getName(),"table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PetContract.PetEntry.TABLE_NAME);
        onCreate(db);
    }

    void deleteDatabase() {
        mContext.deleteDatabase(DATABASE_NAME);
    }
}
