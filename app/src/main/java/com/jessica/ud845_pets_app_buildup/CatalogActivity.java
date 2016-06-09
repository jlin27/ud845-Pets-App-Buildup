package com.jessica.ud845_pets_app_buildup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CatalogActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    private String entryWhereClause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.v(CatalogActivity.class.getName(),"in create");

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
                // startActivityForResult(intent, EDITOR_REQUEST_CODE);
               // Snackbar.make(view, "Add a New Pet", Snackbar.LENGTH_SHORT.setAction("Action", null).show();

            }
        });


        //Gets the data repository in read mode
        PetDbHelper dbHelper = new PetDbHelper(this);
        mDatabase = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database we want to read from
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_AGE,
                PetContract.PetEntry.COLUMN_PET_BREED
        };


        Cursor cursor = mDatabase.query(
                PetContract.PetEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // having
                null,                                     // orderBy
                null);                                    // limit


        // Get the TextView which will be populated with the Pet Table data
        TextView petTextView = (TextView) findViewById(R.id.text_view_pet);


        try {
            petTextView.setText("The Pet Table contains " + cursor.getCount() + " pets\n");
            petTextView.append("COLUMNS: " + PetContract.PetEntry._ID + " - " +
                    PetContract.PetEntry.COLUMN_PET_NAME + " - " +
                    PetContract.PetEntry.COLUMN_PET_AGE + " - " +
                    PetContract.PetEntry.COLUMN_PET_BREED + "\n");


            int nameColumn = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
            int ageColumn = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_AGE);
            int breedColumn = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

            // Iterates through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String value of the word
                // at the current row the cursor is on.

                String currentName = cursor.getString(nameColumn);
                int currentAge = cursor.getInt(ageColumn);
                String currentBreed = cursor.getString(breedColumn);

                petTextView.append(("\n" + currentName + " - " + currentBreed + " - " + currentAge));

                Log.v(CatalogActivity.class.getName(),"in the while loop");

            }
        }
        finally{
            // Always close your cursor to avoid memory leaks
            cursor.close();
            Log.v(CatalogActivity.class.getName(),"closed");
        }

    }

}
