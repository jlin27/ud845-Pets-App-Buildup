package com.jessica.ud845_pets_app_buildup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jessicalin on 6/2/16.
 */
public class PetDbHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "pets.db";

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL Statement to Create the Pets Table
        final String SQL_CREATE_PETS_TABLE =

                "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " (" + PetContract.PetEntry._ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PetContract.PetEntry.COLUMN_PET_TYPE + " INTEGER NOT NULL, "
                        + PetContract.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                        + PetContract.PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                        + PetContract.PetEntry.COLUMN_PET_AGE + " INTEGER, "
                        + PetContract.PetEntry.COLUMN_PET_BREED + " TEXT, "
                        + PetContract.PetEntry.COLUMN_PET_HEIGHT + " INTEGER, "
                        + PetContract.PetEntry.COLUMN_PET_WEIGHT + " INTEGER, "
                        + PetContract.PetEntry.COLUMN_IMAGE_PATH + " TEXT, "
                        + PetContract.PetEntry.COLUMN_STERILIZATION_STATUS + " INTEGER, "
                        + PetContract.PetEntry.COLUMN_ADDITIONAL_NOTES + " TEXT, "
                        + PetContract.PetEntry.COLUMN_ADOPTION_STATUS + " INTEGER )"
                ;

        db.execSQL(SQL_CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}