package com.example.android.edisstartercode.data;

import android.content.ContentProvider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.edisstartercode.data.EdisContract.EdisEntry;
import com.example.android.edisstartercode.data.EdisDbHelper;

import static com.example.android.edisstartercode.data.EdisContract.CONTENT_AUTHORITY;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_ADDRESS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_AGE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_CLASS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_FEES;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GENDER;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_NAME;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CONTENT_ITEM_TYPE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CONTENT_LIST_TYPE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.CONTENT_LIST_TYPE1;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_ONE_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_THREE_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_TWO_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.PRE_NURSERY_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry._ID;
import static com.example.android.edisstartercode.data.EdisContract.PATH_NURSERY_ONE;
import static com.example.android.edisstartercode.data.EdisContract.PATH_NURSERY_THREE;
import static com.example.android.edisstartercode.data.EdisContract.PATH_NURSERY_TWO;
import static com.example.android.edisstartercode.data.EdisContract.PATH_PRE_NURSERY;

/**
 * Created by Delight on 27/04/2018.
 */

public class EdisProvider extends ContentProvider {

    private EdisDbHelper mDbHelper;

    /** Tag for the log messages */
    public static final String LOG_TAG = EdisProvider.class.getSimpleName();

    private static final int EDIS = 100;
    private static final int EDIS_ID = 101;
    private  static final int STUDENT_CLASS= 102;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_PRE_NURSERY, EDIS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_PRE_NURSERY + "/#", EDIS_ID);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_ONE, EDIS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_ONE + "/#", EDIS_ID);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_TWO, EDIS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_TWO + "/#", EDIS_ID);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_THREE, EDIS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_NURSERY_THREE + "/#", EDIS_ID);
    }

    @Override
    public boolean onCreate() {

        mDbHelper = new EdisDbHelper(getContext());
        // TODO: Create and initialize a PetDbHelper object to gain access to the pets database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        return true;
    }

    @Override
    public Cursor query( Uri uri, String[] project, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case  EDIS:
                        cursor = database.query(PRE_NURSERY_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                        cursor = database.query(NURSERY_ONE_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                        cursor = database.query(NURSERY_TWO_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                        cursor = database.query(NURSERY_THREE_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                        break;
                case EDIS_ID:
                    selection = _ID + "=?";
                            selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
                            cursor = database.query(PRE_NURSERY_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                            cursor = database.query(NURSERY_ONE_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                            cursor = database.query(NURSERY_TWO_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                            cursor = database.query(NURSERY_THREE_TABLE, project, selection, selectionArgs, null, null, sortOrder);
                   break;
                default: throw new IllegalArgumentException("Cannot Query Unknown Uri " + uri);
        }
        
        
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public String getType( Uri uri) {
        int match =sUriMatcher.match(uri);
        switch (match){
            case EDIS: return CONTENT_LIST_TYPE;


            case EDIS_ID: return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Uknown uri " + uri  + "with match " + match);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case EDIS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues contentValues) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        // Insert the new pet with the given values
        long id = database.insert(PRE_NURSERY_TABLE, null, contentValues);
        long id1 = database.insert(PRE_NURSERY_TABLE, null, contentValues);
        long id2 = database.insert(NURSERY_ONE_TABLE, null, contentValues);
        long id3 = database.insert(NURSERY_TWO_TABLE, null, contentValues);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1|| id1 == -1 || id2 == -1 || id3 == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }




  /**
          * Updates the data at the given selection and selection arguments, with the new ContentValues.
          */
            @Override
public int update(Uri uri, ContentValues contentValues, String selection,
        String[] selectionArgs) {
final int match = sUriMatcher.match(uri);
        switch (match) {
        case EDIS:
        return updatePet(uri, contentValues, selection, selectionArgs);
        case EDIS_ID:
        // For the PET_ID code, extract out the ID from the URI,
        // so we know which row to update. Selection will be "_id=?" and selection
        // arguments will be a String array containing the actual ID.
        selection = _ID + "=?";
        selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
        return updatePet(uri, contentValues, selection, selectionArgs);
default:
        throw new IllegalArgumentException("Update is not supported for " + uri);
        }
        }

    private int updatePet(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        if (contentValues.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected

        int rowsUpdated = database.update(PRE_NURSERY_TABLE, contentValues, selection, selectionArgs);
        int rowsUpdated1 = database.update(NURSERY_ONE_TABLE, contentValues, selection, selectionArgs);
        int rowsUpdated2 = database.update(NURSERY_TWO_TABLE, contentValues, selection, selectionArgs);
        int rowsUpdated3 = database.update(NURSERY_THREE_TABLE, contentValues, selection, selectionArgs);
        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0 || rowsUpdated1 != 0 || rowsUpdated2 != 0 || rowsUpdated3 != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {



    // Get writeable database
    SQLiteDatabase database = mDbHelper.getWritableDatabase();

    // Track the number of rows that were deleted
    int rowsDeleted;
        int rowsDeleted1;
        int rowsDeleted2;
        int rowsDeleted3;
    final int match = sUriMatcher.match(uri);
        switch (match) {
                case EDIS:

                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(PRE_NURSERY_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_ONE_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_TWO_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_THREE_TABLE, selection, selectionArgs);
                break;
                case EDIS_ID:
                // Delete a single row given by the ID in the URI
                selection = _ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                    rowsDeleted = database.delete(PRE_NURSERY_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_ONE_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_TWO_TABLE, selection, selectionArgs);
                    rowsDeleted = database.delete(NURSERY_THREE_TABLE, selection, selectionArgs);
                break;
default:
        throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
        getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
        }
}
