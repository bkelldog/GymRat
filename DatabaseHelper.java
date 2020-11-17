package com.example.gymrattrial3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    ArrayList<String> userIDList = new ArrayList<String>();

    private static final String LOG = "DATABASEHELPER";
    private static final String DATABASE_NAME = "USER DATABASE";
    private static final int VERSION = 1;

    //There will be three tables:   1. Holds the basic user information
    //                              2. Holds the PRs for each user
    //                              3. Holds the list of brother IDs for each user
    //
    //  Tables 2 and 3 are because the PR list and Brothers list are otherwise very difficult to
    //  Put all together in one table. Lists will coordinate through the IDs.

    //Common to all tables
    private static final String KEY_ID = "id";

    //User Table
    private static final String USER_TABLE_NAME = "user_table";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT);";

    //PR Table
    private static final String PR_TABLE_NAME = "pr_table";
    private static final String BENCH_PR = "bench_pr";
    private static final String SQUAT_PR = "squat_pr";
    private static final String DEADLIFT_PR = "deadlift_pr";
    private static final String ROW_PR = "row_pr";
    private static final String MILE_PR = "mile_pr";
    private static final String CREATE_PR_TABLE = "CREATE TABLE IF NOT EXISTS " + PR_TABLE_NAME + " (" + KEY_ID + " INTEGER, " +
                                                    BENCH_PR + " INTEGER, " +
                                                    SQUAT_PR + " INTEGER, " +
                                                    DEADLIFT_PR + " INTEGER, " +
                                                    ROW_PR + " INTEGER, " +
                                                    MILE_PR + " INTEGER);";

    //Brothers Table
    public static final String BROTHERS_TABLE_NAME = "brothers_table";
    public static final String BROTHER_1 = "brother_1";
    public static final String CREATE_BROTHERS_TABLE = "CREATE TABLE IF NOT EXISTS " + BROTHERS_TABLE_NAME + " (" + KEY_ID + " INTEGER, " + BROTHER_1 + " TEXT);";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PR_TABLE);
        db.execSQL(CREATE_BROTHERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS '" + USER_TABLE_NAME + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + PR_TABLE_NAME + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + BROTHERS_TABLE_NAME + "'");
        onCreate(db);
    }

    public void addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //1. Add user data to USER_TABLE
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, u.getName());
        //db.insert(USER_TABLE_NAME, null, values);
        db.insertWithOnConflict(USER_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //Get User ID
        int id = -1;
        String query  = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + KEY_NAME + " = " + u.getName();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst())
        {
            id = c.getInt(c.getColumnIndex(KEY_ID));
        }

        if (id == -1)
            return;

        //2. Add user PR list to PR_TABLE
        values = new ContentValues();
        values.put(KEY_ID, id);
        int[] pr_list = u.getPRList();
        for (int i = 0; i < pr_list.length; i++)
        {
            String col = "";
            switch(i)
            {
                case 0:
                    col = BENCH_PR;
                    break;
                case 1:
                    col = SQUAT_PR;
                    break;
                case 2:
                    col = DEADLIFT_PR;
                    break;
                case 3:
                    col = ROW_PR;
                    break;
                case 4:
                    col = MILE_PR;
            }
            values.put(col, pr_list[i]);
        }
        db.insertWithOnConflict(PR_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //3. Add brothers list to BROTHER_TABLE
        values = new ContentValues();
        values.put(KEY_ID, id);
        //ArrayList<String> brotherIDList = u.getBrothersList();
    }

    public void updateUser()
    {

    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> userList = new ArrayList<User>();

        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst())
        {
            do {
                User u = new User();
                u.setID(c.getInt(c.getColumnIndex(KEY_ID)));
                u.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            } while (c.moveToNext());
        }
        c.close();
        return userList;
    }

}

    /*
    /*
    This class will handle all events relating to the SQLite database. It will act
    as a driver. We will be able to create a new table, edit rows and cols, insert
    data into table, and all other SQL actions.
     /

    private static final String TAG = "DatabaseHelper";

    private String TABLE_NAME = "";
    private static final String COL1 = "id";
    private static final String COL2 = "first_name";
    private static final String COL3 = "last_name";
    private static final String COL4 = "email";
    private static final String COL5 = "password";
    private static final String COL6 = "race";
    private static final String COL7 = "gender";
    private static final String COL8 = "age";
    private static final String COL9 = "height";
    private static final String COL10 = "weight";
    private static final String COL11 = "goals";
    private static final String COL12 = "gym_id";
    private static final String COL13 = "pr_list";
    private static final String COL14 = "brother_list";
    //IN THAT ORDER ^^

    public DatabaseHelper(Context context, String table_name)
    {
        super(context, table_name, null, 1);
        TABLE_NAME = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE " +
                TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT, " +
                COL7 + " TEXT, " +
                COL8 + " INTEGER, " +
                COL9 + " INTEGER, " +
                COL10 + " INTEGER, " +
                COL11 + " TEXT, " +
                COL12 + " TEXT, " +
                COL13 + " INTEGER, " +
                COL14 + " TEXT";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createTable(String table_name)
    {

    }

    public boolean addFirstName(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean addLastName(String lastName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, lastName);

        Log.d(TAG, "addData: Adding " + lastName + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean addAge(int age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, age);

        Log.d(TAG, "addData: Adding " + age + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1)
            return false;
        else
            return true;
    }

    //Returns all data from the database
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    //Returns ID that matches the name passed in
    //@param firstName
    //@return ID
    public Cursor getItemIDByFirstName(String firstName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + firstName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemIDByLastName(String lastName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL3 + " = '" + lastName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemIDByEmail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL4 + " = '" + email + "'";
        if (query == null)
            return null;

        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemIDByPassword(String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL5 + " = '" + pass + "'";

        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Updates the name field
    //@params newName, id, oldName
    public void updateName(String newName, int id, String oldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + id + "'" + " AND " + COL2 + " = '" + oldName + "'";

        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);

        db.execSQL(query);
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + name + "'";

        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");

        db.execSQL(query);
    }
}
*/