package com.example.gymrattrial3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TableManager extends SQLiteOpenHelper {

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

    public TableManager(Context context, String table_name)
    {
        super(context, table_name, null, 1);
        TABLE_NAME = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE IF NOT EXISTS " +
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
                COL11 + " TEXT)";
               // COL12 + " TEXT, " +
               // COL13 + " INTEGER, " +
               // COL14 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User u)
    {
       // String query = "INSERT INTO " + TABLE_NAME + " VALUES (" + u.getDataTabulated() + ")";

      //  super.getWritableDatabase().execSQL(query);
    }
}
