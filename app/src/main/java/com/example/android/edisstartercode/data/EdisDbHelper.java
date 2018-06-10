package com.example.android.edisstartercode.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_ADDRESS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_AGE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_CLASS;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_FEES;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GENDER;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN_OCCUPATION;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN_PHONENUMBER;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_NAME;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_ONE_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_THREE_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.NURSERY_TWO_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.PRE_NURSERY_TABLE;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry._ID;

/**
 * Created by Delight on 27/04/2018.
 */

public class EdisDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "edis.db";

    public EdisDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_PRENURSERY_TABLE = "CREATE TABLE " + PRE_NURSERY_TABLE + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_STUDENT_CLASS + " INTEGER, "
                + COLUMN_STUDENT_GENDER + " INTEGER, "
                + COLUMN_STUDENT_FEES + " INTEGER NOT NULL, "
                + COLUMN_STUDENT_ADDRESS + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_PHONENUMBER + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_OCCUPATION + " TEXT NOT NULL );";
        final String SQL_NURSERY_ONE_TABLE = "CREATE TABLE " + NURSERY_ONE_TABLE + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_STUDENT_CLASS + " INTEGER, "
                + COLUMN_STUDENT_GENDER + " INTEGER, "
                + COLUMN_STUDENT_FEES + " INTEGER NOT NULL, "
                + COLUMN_STUDENT_ADDRESS + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_PHONENUMBER + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_OCCUPATION + " TEXT NOT NULL );";
        final String SQL_NURSERY_TWO_TABLE = "CREATE TABLE " + NURSERY_TWO_TABLE + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_STUDENT_CLASS + " INTEGER, "
                + COLUMN_STUDENT_GENDER + " INTEGER, "
                + COLUMN_STUDENT_FEES + " INTEGER NOT NULL, "
                + COLUMN_STUDENT_ADDRESS + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_PHONENUMBER + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_OCCUPATION + " TEXT NOT NULL );";

        final String SQL_NURSERY_THREE_TABLE = "CREATE TABLE " + NURSERY_THREE_TABLE + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_STUDENT_CLASS + " INTEGER, "
                + COLUMN_STUDENT_GENDER + " INTEGER, "
                + COLUMN_STUDENT_FEES + " INTEGER NOT NULL, "
                + COLUMN_STUDENT_ADDRESS + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_PHONENUMBER + " TEXT NOT NULL, "
                + COLUMN_STUDENT_GUARDIAN_OCCUPATION + " TEXT NOT NULL );";
            sqLiteDatabase.execSQL(SQL_PRENURSERY_TABLE);
            sqLiteDatabase.execSQL(SQL_NURSERY_ONE_TABLE);
            sqLiteDatabase.execSQL(SQL_NURSERY_TWO_TABLE);
            sqLiteDatabase.execSQL(SQL_NURSERY_THREE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
