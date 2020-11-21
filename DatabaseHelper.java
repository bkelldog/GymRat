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
    //  put all together in one table. Lists will coordinate through the IDs.

    //Common to all tables
    private static final String KEY_ID = "id";

    //User Table
    // __________________________________________________________________
    // |ID|firstname|lastname|email|password|race|gender|   |   |   |    |
    // |  |         |        |     |        |    |      |   |   |   |    |
    // |  |         |        |     |        |    |      |   |   |   |    |
    // |__|_________|________|_____|________|____|______|___|___|___|____|
    private static final String USER_TABLE_NAME = "user_table";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_RACE = "race";
    private static final String KEY_GENDER = "gender";
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_FIRST_NAME + " TEXT, " +
            KEY_LAST_NAME + " TEXT, " +
            KEY_EMAIL + " TEXT, " +
            KEY_PASSWORD + " TEXT, " +
            KEY_RACE + " TEXT, " +
            KEY_GENDER + " TEXT);";

    //PR Table
    private static final String PR_TABLE_NAME = "pr_table";
    private static final String BENCH_PR = "bench_pr";
    private static final String SQUAT_PR = "squat_pr";
    private static final String DEADLIFT_PR = "deadlift_pr";
    private static final String ROW_PR = "row_pr";
    private static final String MILE_PR = "mile_pr";
    private static final String CREATE_PR_TABLE = "CREATE TABLE IF NOT EXISTS " + PR_TABLE_NAME + " (" +
            KEY_ID + " INTEGER, " +
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
        Log.d("TAG", "DATABASE INITIALIZE");
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
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + PR_TABLE_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + BROTHERS_TABLE_NAME + "");
        onCreate(db);
    }

    public int getIDByEmail(String email)
    {
        //We will assume that the name is the first name
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + KEY_ID + " FROM " + USER_TABLE_NAME + " WHERE " + KEY_EMAIL + " = '" + email + "';";
        Cursor c = db.rawQuery(query, null);

        Integer id = null;
        if (c.moveToFirst())
        {
            id = c.getInt(c.getColumnIndex(KEY_ID));
        }
        c.close();

        return id;
    }

    public boolean addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //1. Add user data to USER_TABLE
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, u.getFirstName());
        values.put(KEY_LAST_NAME, u.getLastName());
        values.put(KEY_EMAIL, u.getEmail());
        values.put(KEY_PASSWORD, u.getPassword());
        values.put(KEY_RACE, u.getRace());
        values.put(KEY_GENDER, u.getGender());
        db.insert(USER_TABLE_NAME, null, values);
        //db.insertWithOnConflict(USER_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //Get User ID
        int id = -1;
        String query  = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + KEY_EMAIL + " = '" + u.getEmail() + "';";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst())
        {
            id = c.getInt(c.getColumnIndex(KEY_ID));
        }
        if (id == -1)
            return false;
        u.setID(id);
        values = new ContentValues();
        values.put(KEY_ID, u.getID());
        db.insert(USER_TABLE_NAME, null, values);

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
        db.insert(PR_TABLE_NAME, null, values);
        //db.insertWithOnConflict(PR_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //3. Add brothers list to BROTHER_TABLE
        values = new ContentValues();
        values.put(KEY_ID, id);
        ArrayList<String> brotherIDList = u.getBrothersList();
        String brother_key = "brother_";
        for (int i = 0; i < brotherIDList.size(); i++)
            values.put(brother_key + "" +  i, id);
        db.insert(BROTHERS_TABLE_NAME, null, values);

        c.close();
        return true;
    }

    public void updateUser(String email, String col, String value)
    {
        if (!isColumn(col))
            return;
        String query = "UPDATE " + USER_TABLE_NAME + " SET " + col + " = '" + value + "' WHERE " + KEY_EMAIL + " = '" + email + "';";
        this.getWritableDatabase().execSQL(query);
    }

    public void updateUser(User u)
    {
        String email = u.getEmail();
        if (email != null)
        {
            updateUser(email, KEY_ID, "" + u.getID());
            updateUser(email, KEY_FIRST_NAME, u.getFirstName());
            updateUser(email, KEY_LAST_NAME, u.getLastName());
            updateUser(email, KEY_EMAIL, u.getEmail());
            updateUser(email, KEY_PASSWORD, u.getPassword());
            updateUser(email, KEY_RACE, u.getRace());
            updateUser(email, KEY_GENDER, u.getGender());
        }
        else
        {
            this.getWritableDatabase().execSQL("UPDATE " + USER_TABLE_NAME + " SET " + KEY_ID + " = '" + u.getID() + " WHERE " );
        }
    }

    public void updateUsers(ArrayList<User> userList)
    {
        for (User u : userList)
            updateUser(u);
    }

    private boolean isColumn(String col)
    {
        switch(col)
        {
            case KEY_FIRST_NAME:
                return true;
            case KEY_LAST_NAME:
                return true;
            case KEY_EMAIL:
                return true;
            case KEY_PASSWORD:
                return true;
            case KEY_RACE:
                return true;
            case KEY_GENDER:
                return true;
        }
        return false;
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
                u.setFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
                u.setLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
                u.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                u.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
                userList.add(u);
            } while (c.moveToNext());
        }
        c.close();
        return userList;
    }

    public ArrayList<String> getBrothersList(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + KEY_EMAIL + " = '" + email + "';";
        Cursor c = db.rawQuery(query, null);
        c.close();
        return null;
    }

}
