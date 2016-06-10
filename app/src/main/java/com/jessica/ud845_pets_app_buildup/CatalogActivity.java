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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CatalogActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private CursorAdapter mCursorAdapter;
    private ListView mMainListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        // Get the ListView which will be populated with the Pet Table data
        ListView petListView = (ListView) findViewById(R.id.catalog_list_view);

            // Specify which columns from the Cursor you want to use in the layout
            String[] data_from = {PetContract.PetEntry.COLUMN_PET_NAME, PetContract.PetEntry.COLUMN_PET_AGE};
            // Specify the views you want to populate with the data from data_from
            int[] data_to = {android.R.id.text1, android.R.id.text2};

            // Set the Adapter to fill the standard two_line_list_item layout with data from the Cursor.
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.two_line_list_item,
                    cursor,
                    data_from,
                    data_to,
                    0);

            Log.v(CatalogActivity.class.getName(),"before settingAdapter");

            // Attach the adapter to the ListView.
            petListView.setAdapter(adapter);

    }

}
