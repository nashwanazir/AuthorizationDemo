package com.example.asus.authorizationdemo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asus.authorizationdemo.model.Student;

/**
 * Created by Asus on 5/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Students.db";

    // User table name
    private static final String TABLE_STUDENT = "student";

    // User Table Columns names
    private static final String COLUMN_STUDENT_ID = "student_id";
    private static final String COLUMN_STUDENT_NAME = "student_name";
    private static final String COLUMN_STUDENT_EMAIL = "student_email";
    private static final String COLUMN_STUDENT_PASSWORD = "student_password";

    // create table sql query
    private String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STUDENT_NAME + " TEXT,"
            + COLUMN_STUDENT_EMAIL + " TEXT," + COLUMN_STUDENT_PASSWORD + " TEXT" + ")";


    /**
     * Constructor
     *
     * @param context,dbname,factory,dbversion
     */

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // **************************************************
    // Public methods
    // **************************************************

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS" + TABLE_STUDENT;
        onCreate(db);

    }

    // Insert student into database
    public void addStudent(Student student) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, student.getName());
        values.put(COLUMN_STUDENT_EMAIL, student.getEmail());
        values.put(COLUMN_STUDENT_PASSWORD, student.getPassword());

        // Inserting Row
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }


    public String getName(String email){

        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT student_name from student WHERE student_email='"+email+"'";
        Cursor c=db.rawQuery(query,null);
        String name = null;
        if (c.moveToFirst()) {
            do{
                name = c.getString(c.getColumnIndex("student_name"));
            }while (c.moveToNext());

        }
            return name;

    }

    // Check student exist or not
     public boolean check(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        // columns to fetch
        String[] columns = {
                COLUMN_STUDENT_ID
        };

        // selection criteria
        String selection = COLUMN_STUDENT_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query student table with condition
        /* SELECT student_id FROM student WHERE student_email = 'abc@xyz.com';
         */

        // db.query(table_name,table_columns,selection,selectionArgs,groupBy,having,orderBy)
        Cursor cursor = db.query(TABLE_STUDENT, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
            return true;
        }

        return false;
    }

    // check student exist or not
    public boolean check(String email, String password) {

        // columns to fetch
        String[] columns = {
                COLUMN_STUDENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND " + COLUMN_STUDENT_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /*         *
         SELECT student_id FROM student WHERE student_email = 'abc@xyz.com' AND student_password = 'prqst';
         */

        // db.query(table_name,table_columns,selection,selectionArgs,groupBy,having,orderBy)
        Cursor cursor = db.query(TABLE_STUDENT, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();

        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        }

        return false;
    }

}

