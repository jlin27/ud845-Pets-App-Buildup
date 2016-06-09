package com.jessica.ud845_pets_app_buildup;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText breedEditText;
    private Button insertDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context. We use this because we are in the activity class
        PetDbHelper dbHelper = new PetDbHelper(this);

        // Gets the data repository in write mode
        mDatabase = dbHelper.getWritableDatabase();


        nameEditText = (EditText) findViewById(R.id.edit_pet_name);
        breedEditText = (EditText) findViewById(R.id.edit_pet_breed);
        ageEditText = (EditText) findViewById(R.id.edit_pet_age);

        insertDataButton = (Button) findViewById(R.id.button_insert_data);

        insertDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPet();
            }
        });

    }



    private void insertPet(){

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        //TODO: explain use trim to eliminate leading or trailing white space
        String name = nameEditText.getText().toString().trim();
        String breed = breedEditText.getText().toString().trim();

        values.put(PetContract.PetEntry.COLUMN_PET_NAME, name);
        values.put(PetContract.PetEntry.COLUMN_PET_BREED, breed);


        if (!ageEditText.getText().toString().trim().equals("")) {
            int age = Integer.parseInt(ageEditText.getText().toString().trim());
            values.put(PetContract.PetEntry.COLUMN_PET_AGE, age);
        }

        /** Insert the new row, returning the primary key value of the new row.
         * The first argument for insert() is the table name.
         * The second argument provides the name of a column in which the framework
         * can insert NULL in the event that the ContentValues is empty (if
         * this is set to "null", then the framework will not insert a row when
         * there are no values).
         */
        long newRowId = mDatabase.insert(PetContract.PetEntry.TABLE_NAME,null,values);
        if(newRowId != -1)
            Toast.makeText(this, "New row added, row id: " + newRowId, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();



    }
}
