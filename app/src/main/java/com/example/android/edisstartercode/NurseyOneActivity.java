package com.example.android.edisstartercode;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.edisstartercode.data.EdisCursorAdapter;

import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_ADDRESS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_AGE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_CLASS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_FEES;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GENDER;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN_OCCUPATION;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN_PHONENUMBER;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_NAME;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CONTENT_URI;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CONTENT_URI1;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry._ID;

public class NurseyOneActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EdisCursorAdapter mCursorAdapter;

    private static final int PET_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursey_one);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(NurseyOneActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        //Find the ListView which will be populated with the pet data
        ListView petListView = (ListView) findViewById(R.id.list);

        //Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

        //Set up an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes ) so pass in null for the Cursor.
        mCursorAdapter = new EdisCursorAdapter(this, null);
        petListView.setAdapter(mCursorAdapter);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(NurseyOneActivity.this, EditorActivity.class);

                // Form the content URI that represents the specific pet that was clicked on,
                // by appending the "id" ( passed as input to this method) onto the
                // {@link PetEntry#CONTENT_URI}
                // For example, the URI would be "content://com.example.android.pets/pets/2"
                // if the pet with ID 2 was clicked on.
                Uri currentPetUri =  ContentUris.withAppendedId(CONTENT_URI, id);

                //Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                //Launch the {@link EditorActivity} to display the data for the current pet.
                startActivity(intent);
            }
        });

        // Kick off the Loader
        getLoaderManager().initLoader(PET_LOADER, null, this);

    }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                _ID,
                COLUMN_STUDENT_NAME,
                COLUMN_STUDENT_AGE,
                COLUMN_STUDENT_CLASS,
                COLUMN_STUDENT_GENDER,
                COLUMN_STUDENT_FEES,
                COLUMN_STUDENT_ADDRESS,
                COLUMN_STUDENT_GUARDIAN,
                COLUMN_STUDENT_GUARDIAN_PHONENUMBER,
                COLUMN_STUDENT_GUARDIAN_OCCUPATION

        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this, // Parent activity context
                CONTENT_URI1,       // Query the content URI for the current pet
                projection,            // Columns to include in the resulting Cursor
                null,                   //No selection clause
                null,                   //No selection arguments
                null);                  //Default sort order
    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }



}



