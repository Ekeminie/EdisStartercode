package com.example.android.edisstartercode;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_JSS_ONE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_JSS_THREE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_JSS_TWO;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_NURSERY_ONE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_NURSERY_THREE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_NURSERY_TWO;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRE_NURSERY;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRIMARY_FIVE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRIMARY_FOUR;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRIMARY_ONE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRIMARY_THREE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_PRIMARY_TWO;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_SS_ONE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_SS_THREE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_SS_TWO;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CLASS_UNKNOWN;
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
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.GENDER_FEMALE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.GENDER_MALE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.GENDER_UNKNOWN;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry._ID;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    // Identifier for the pet data loader
    private static final int EXISTING_PET_LOADER = 0;

    private Uri mCurrentStudentUri;

    private int mGender = 0;

    private int mClass = 0;

    /**
     * EditText field to enter the student's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the student's age
     */
    private EditText mAgeEditText;

    /**
     * EditText field to enter the student's fees
     */
    private EditText mFeesEditText;

    /**
     * EditText field to enter the student's address
     */
    private EditText mAddressEditText;

    /**
     * Spinner for the gender selection
     */
    private Spinner mGenderSpinner;

    /**
     * Spinner for the class selection
     */
    private Spinner mClassSpinner;


    /**
     * EditText field to enter the student's guardian name
     */
    private EditText mGuardianNameEditText;

    /**
     * EditText field to enter the student's guardian phone number
     */
    private EditText mGuardianNumberEditText;


    /**
     * EditText field to enter the student's guardian occupation
     */
    private EditText mGuardianOccupationEditText;

    private boolean mStudentHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mStudentHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        // Examine the intent that was used to launch this activity
        // in order to figure otu if we're creating a new pet or editing an existing one
        Intent intent = getIntent();
        mCurrentStudentUri = intent.getData();

        // If the intent DOES NOT contain a pet content URI, then we know that we are
        // creating a new pet
        if (mCurrentStudentUri == null) {
            // This is a new pet, so change the app bar to say "Add a pet"
            setTitle(getString(R.string.editor_activity_title_new_student));

            //Invalidate the options menu, so the "Delete menu option can be hidden.
            // It doesn't make sense to delete a pet that hasn't been created yet. }
            invalidateOptionsMenu();
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle(getString(R.string.editor_activity_title_edit_student));

            getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);

        }


        //These are all the relevant views we will need to read user input from
        mNameEditText = findViewById(R.id.name_text_field);
        mAgeEditText = findViewById(R.id.age_text_field);
        mFeesEditText = findViewById(R.id.feess_text_field);
        mAddressEditText = findViewById(R.id.adress_text_field);
        mGuardianNameEditText = findViewById(R.id.guardian_name_text_field);
        mGuardianNumberEditText = findViewById(R.id.phone_number_text_field);
        mGuardianOccupationEditText = findViewById(R.id.occupation_text_field);
        mGenderSpinner =findViewById(R.id.spinner_gender);
        mClassSpinner = findViewById(R.id.spinner_class);

        //set on touch listeners to the views associated with the data we want.
        mNameEditText.setOnTouchListener(mTouchListener);
        mAgeEditText.setOnTouchListener(mTouchListener);
        mFeesEditText.setOnTouchListener(mTouchListener);
        mAddressEditText.setOnTouchListener(mTouchListener);
        mGuardianNameEditText.setOnTouchListener(mTouchListener);
        mGuardianNumberEditText.setOnTouchListener(mTouchListener);
        mGuardianOccupationEditText.setOnTouchListener(mTouchListener);

        setupSpinner();
        setupClassesSpinner();

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.male_gender))) {
                        mGender = GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.female_gender))) {
                        mGender = GENDER_FEMALE; // Female
                    } else {
                        mGender = GENDER_UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });

    }

    public void setupClassesSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.classes_option, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mClassSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.pre_nursery))) {
                        mClass = CLASS_PRE_NURSERY; // Pre-Nursery
                    } else if (selection.equals(getString(R.string.nursery_one))) {
                        mClass = CLASS_NURSERY_ONE; // Nursery One
                    } else if (selection.equals(getString(R.string.nursery_two))) {
                        mClass = CLASS_NURSERY_TWO; // Nursery Two
                    } else if (selection.equals(getString(R.string.transition))) {
                        mClass = CLASS_NURSERY_THREE; // Transition
                    } else if (selection.equals(getString(R.string.basic_one))) {
                        mClass = CLASS_PRIMARY_ONE; // Primary One
                    } else if (selection.equals(getString(R.string.basic_two))) {
                        mClass = CLASS_PRIMARY_TWO; //  Primary Two
                    } else if (selection.equals(getString(R.string.basic_three))) {
                        mClass = CLASS_PRIMARY_THREE; // Primary Three
                    } else if (selection.equals(getString(R.string.basic_four))) {
                        mClass = CLASS_PRIMARY_FOUR; // Primary Four
                    } else if (selection.equals(getString(R.string.basic_five))) {
                        mClass = CLASS_PRIMARY_FIVE; // Primary Five
                    } else if (selection.equals(getString(R.string.jss_one))) {
                        mClass = CLASS_JSS_ONE; //JSS ONE
                    } else if (selection.equals(getString(R.string.jss_two))) {
                        mClass = CLASS_JSS_TWO; //JSS TWO
                    } else if (selection.equals(getString(R.string.jss_three))) {
                        mClass = CLASS_JSS_THREE; //JSS THREE
                    } else if (selection.equals(getString(R.string.ss_one))) {
                        mClass = CLASS_SS_ONE; //SS ONE
                    } else if (selection.equals(getString(R.string.ss_two))) {
                        mClass = CLASS_SS_TWO; //SS TWO
                    } else if (selection.equals(getString(R.string.ss_one))) {
                        mClass = CLASS_SS_THREE; //SS THREE
                    } else {
                        mClass = CLASS_UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mClass = CLASS_UNKNOWN; // Unknown
            }
        });

    }

    public void showDeleteConfirmationDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //user pressed the delete button, so delete students detail using the method called below
                deleteStudent();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //user cancelled this dialog so dismiss the dialog interface
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        //if this is a new student, hide the delete menu options
        if (mCurrentStudentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveStudent();
                // Do nothing for now
                //save pet when clicked on and return to main activity

                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    public void saveStudent() {
        String nameData = mNameEditText.getText().toString().trim();
        String ageData = mAgeEditText.getText().toString().trim();
        String feesData = mFeesEditText.getText().toString().trim();
        String addressData = mAddressEditText.getText().toString().trim();
        String guardianNameData = mGuardianNameEditText.getText().toString().trim();
        String guardianNumberData = mGuardianNumberEditText.getText().toString().trim();
        String guardianOccupationData = mGuardianOccupationEditText.getText().toString().trim();

        if (mCurrentStudentUri == null
                && TextUtils.isEmpty(nameData)
                && TextUtils.isEmpty(ageData)
                && TextUtils.isEmpty(feesData)
                && TextUtils.isEmpty(addressData)
                && TextUtils.isEmpty(guardianNameData)
                && TextUtils.isEmpty(guardianNumberData)
                && TextUtils.isEmpty(guardianOccupationData)
                && mGender == GENDER_UNKNOWN
                && mClass == CLASS_UNKNOWN) {
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }
        //Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, nameData);
        values.put(COLUMN_STUDENT_AGE, ageData);
        values.put(COLUMN_STUDENT_FEES, feesData);
        values.put(COLUMN_STUDENT_ADDRESS, addressData);
        values.put(COLUMN_STUDENT_GUARDIAN, guardianNameData);
        values.put(COLUMN_STUDENT_GUARDIAN_PHONENUMBER, guardianNumberData);
        values.put(COLUMN_STUDENT_GUARDIAN_OCCUPATION, guardianOccupationData);
        values.put(COLUMN_STUDENT_GENDER, mGender);
        values.put(COLUMN_STUDENT_CLASS, mClass);

        // Determine if this is a new or existing pet by checking if mCurrentPetUri is null or not
        if (mCurrentStudentUri == null) {
            //This is a NEW pet, so insert a new pet into the provider.
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(CONTENT_URI, values);

            //Show a toast message depending on whether or not the insertion was successful
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, R.string.editor_insert_student_failed, Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_insert_student_successful) + newUri, Toast.LENGTH_SHORT).show();
            }

        } else {
            // Otherwise this is an EXISTING pet, so update the pet with content URI: mCurrentPetUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentPetUri will already identify the correct row in the database that
            // we want to modify
            int rowsAffected = getContentResolver().update(mCurrentStudentUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful
            if (rowsAffected == 0) {
                // if no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_student_failed), Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast
                Toast.makeText(this, getString(R.string.editor_update_student_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteStudent() {
        // Only perform the delete if this is an existing pet.
        if (mCurrentStudentUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI
            // Pass in null for the selection adn selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want
            int rowsDeleted = getContentResolver().delete(mCurrentStudentUri, null, null);

            // Show a toast message depending on whether or not the delete was successful
            if (rowsDeleted == 0) {
                // if no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_student_failed), Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_student_successful), Toast.LENGTH_SHORT).show();
            }
        }
        // CLose the activity
        finish();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all pet attributes, define a projection that contains
        // all columns form the pet table
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
                mCurrentStudentUri,       // Query the content URI for the current pet
                projection,            // Columns to include in the resulting Cursor
                null,                   //No selection clause
                null,                   //No selection arguments
                null);                  //Default sort order
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mNameEditText.setText("");
        mAgeEditText.setText("");
        mFeesEditText.setText("");
        mAddressEditText.setText("");
        mGuardianNameEditText.setText("");
        mGuardianNumberEditText.setText("");
        mGuardianOccupationEditText.setText("");
        mGenderSpinner.setSelection(GENDER_UNKNOWN);
        mClassSpinner.setSelection(CLASS_UNKNOWN);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1){
            return;
        }
        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if(cursor.moveToFirst()){
            //Find the columns of student attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_NAME);
            int ageColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_AGE);
            int classColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_CLASS);
            int genderColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_GENDER);
            int feesColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_FEES);
            int addressColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_ADDRESS);
            int guardianColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_GUARDIAN);
             int guardianPhoneColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_GUARDIAN_PHONENUMBER);
             int guardianOccupationColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_GUARDIAN_OCCUPATION);

             String name = cursor.getString(nameColumnIndex);
             String age = cursor.getString(ageColumnIndex);
             int classs = cursor.getInt(classColumnIndex);
             int gender = cursor.getInt(genderColumnIndex);
             String fees = cursor.getString(feesColumnIndex);
             String address = cursor.getString(addressColumnIndex);
             String guardianName = cursor.getString(guardianColumnIndex);
             String guardianPhone = cursor.getString(guardianPhoneColumnIndex);
             String guardianOccupation = cursor.getString(guardianOccupationColumnIndex);

            //Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            mAgeEditText.setText(age);
            mFeesEditText.setText(fees);
            mAddressEditText.setText(address);
            mGuardianNameEditText.setText(guardianName);
            mGuardianNumberEditText.setText(guardianPhone);
            mGuardianOccupationEditText.setText(guardianOccupation);

            //Gender and Class are dropdown spinners, so map the constant value from the database
            // into one of the dropdown options(0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection
            switch (gender){
                case GENDER_MALE:
                    mGenderSpinner.setSelection(1);
                    break;
                case GENDER_FEMALE:
                    mGenderSpinner.setSelection(2);
                    break;
                default:
                    mGenderSpinner.setSelection(0);
                    break;
            }


            switch (classs){
                case CLASS_PRE_NURSERY:
                    mClassSpinner.setSelection(1);
                    break;
                case CLASS_NURSERY_ONE:
                     mClassSpinner.setSelection(2);
                    break;
                case CLASS_NURSERY_TWO:
                    mClassSpinner.setSelection(3);
                    break;
                case CLASS_NURSERY_THREE:
                    mClassSpinner.setSelection(4);
                    break;
                case CLASS_PRIMARY_ONE:
                    mClassSpinner.setSelection(5);
                    break;
                case CLASS_PRIMARY_TWO:
                    mClassSpinner.setSelection(6);
                    break;
                case CLASS_PRIMARY_THREE:
                    mClassSpinner.setSelection(7);
                    break;
                case CLASS_PRIMARY_FOUR:
                    mClassSpinner.setSelection(8);
                    break;
                case CLASS_PRIMARY_FIVE:
                    mClassSpinner.setSelection(9);
                    break;
                case CLASS_JSS_ONE:
                    mClassSpinner.setSelection(10);
                    break;
                case CLASS_JSS_TWO:
                    mClassSpinner.setSelection(11);
                    break;
                case CLASS_JSS_THREE:
                    mClassSpinner.setSelection(12);
                    break;
                case CLASS_SS_ONE:
                    mClassSpinner.setSelection(13);
                    break;
                case CLASS_SS_TWO:
                    mClassSpinner.setSelection(14);
                    break;
                case CLASS_SS_THREE:
                    mClassSpinner.setSelection(15);
                    default:
                        mClassSpinner.setSelection(0);
                        break;

            }
      }
    }

}
