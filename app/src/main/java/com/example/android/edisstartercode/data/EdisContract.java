package com.example.android.edisstartercode.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Delight on 26/04/2018.
 */

public final class EdisContract {

    private EdisContract(){}

    public static final String  PATH_PRE_NURSERY = "Pre-Nursery";
    public static final String PATH_NURSERY_ONE = "Nursery-One";
    public static final String PATH_NURSERY_TWO = "Nursery-Two";
    public static final String PATH_NURSERY_THREE = "Nursery-Three";


    public static final String CONTENT_AUTHORITY = "com.example.android.edis";
    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /*Possible path (appended to base content URI for possible URI's)
    * For instance, content://com.android.pets/edis/ is a valid path for
    * looking at pet data. content://com.android.edis/staff/ will fail.
    * as the ContentProvider hasn't been given any information on waht to do with staff
    * */
    public static final class EdisEntry implements BaseColumns{



        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRE_NURSERY);
        public static final Uri CONTENT_URI1 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NURSERY_ONE);
        public static final Uri CONTENT_URI2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NURSERY_TWO);
        public static final Uri CONTENT_URI3 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NURSERY_THREE);
        public static final String PRE_NURSERY_TABLE= "Pre-Nursery";
        public static final String NURSERY_ONE_TABLE ="Nursery-One";
        public static final String NURSERY_TWO_TABLE ="Nursery-Two";
        public static final String NURSERY_THREE_TABLE ="Nursery-Three";

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRE_NURSERY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRE_NURSERY;

        public static final String CONTENT_LIST_TYPE1 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_ONE;
        public static final String CONTENT_ITEM_TYPE1 = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_ONE;

        public static final String CONTENT_LIST_TYPE2 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_TWO;
        public static final String CONTENT_ITEM_TYPE2 = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_TWO;

        public static final String CONTENT_LIST_TYPE3 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_THREE;
        public static final String CONTENT_ITEM_TYPE3 = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NURSERY_THREE;

        /**
         * These are the table constants for the database
         *
         */
        public final static String _ID= BaseColumns._ID;
        public final  static String COLUMN_STUDENT_NAME ="name";
        public final  static String COLUMN_STUDENT_AGE ="age";
        public final  static String COLUMN_STUDENT_CLASS ="class";
        public final  static String COLUMN_STUDENT_FEES ="fees";
        public final  static String COLUMN_STUDENT_GENDER ="gender";
        public final  static String COLUMN_STUDENT_ADDRESS ="address";
        public final  static String COLUMN_STUDENT_GUARDIAN ="guardian";
        public final  static String COLUMN_STUDENT_GUARDIAN_PHONENUMBER ="phone_number";
        public final  static String COLUMN_STUDENT_GUARDIAN_OCCUPATION ="guardian_occupation";


        //These values will be used to check and set the gender in the main activity
        public static final int GENDER_MALE= 1;
        public static final int GENDER_FEMALE= 2;
        public static final int GENDER_UNKNOWN= 0;

        //These values will be used to check and set the classes of the pupils
        public static final int CLASS_UNKNOWN= 0;
        public static final int CLASS_PRE_NURSERY= 1;
        public static final int CLASS_NURSERY_ONE= 2;
        public static final int CLASS_NURSERY_TWO= 3;
        public static final int CLASS_NURSERY_THREE= 4;
        public static final int CLASS_PRIMARY_ONE= 5;
        public static final int CLASS_PRIMARY_TWO= 6;
        public static final int CLASS_PRIMARY_THREE= 7;
        public static final int CLASS_PRIMARY_FOUR= 8;
        public static final int CLASS_PRIMARY_FIVE= 9;
        public static final int CLASS_JSS_ONE= 10;
        public static final int CLASS_JSS_TWO= 11;
        public static final int CLASS_JSS_THREE= 12;
        public static final int CLASS_SS_ONE= 13;
        public static final int CLASS_SS_TWO= 14;
        public static final int CLASS_SS_THREE= 15;


        public static boolean isValidClass(int classs){
            if(classs == CLASS_PRE_NURSERY || classs == CLASS_NURSERY_ONE || classs == CLASS_NURSERY_TWO ||
                    classs == CLASS_NURSERY_THREE || classs == CLASS_PRIMARY_ONE || classs == CLASS_PRIMARY_TWO
            || classs == CLASS_PRIMARY_THREE || classs == CLASS_PRIMARY_FOUR || classs == CLASS_PRIMARY_FIVE ||
                    classs == CLASS_JSS_ONE || classs == CLASS_JSS_TWO || classs == CLASS_JSS_THREE
                    || classs == CLASS_SS_ONE || classs == CLASS_SS_TWO || classs == CLASS_SS_THREE){
                return true;
            }
            return false;
        }

        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }

        public static boolean isValidName(String name){
            String mName = name.trim();
            if (mName != null){
                return true;
            }
            return false;
        }
        public static boolean isValidAge(int age) {
            if (age>=4 && age <=21) {
                return true;
            }
            return false;
        }

        public static boolean isValidFees(int fees) {
            if (fees>=1000 && fees <=40000) {
                return true;
            }
            return false;
        }



        public static boolean isValidAddress(String address){
            String mAddress = address.trim();
            if (mAddress != null){
                return true;
            }
            return false;
        }

        public static boolean isValidGuardianName(String guardianName){
            String mGuardianName = guardianName.trim();
            if (mGuardianName!= null){
                return true;
            }
            return false;
        }

        public static boolean isValidGuardianPhone(String guardianPhone){
            String mGuardianName = guardianPhone.trim();
            if (mGuardianName!= null){
                return true;
            }
            return false;
        }






    }
}
