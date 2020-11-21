package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG", "INITIALIZE");

        db = new DatabaseHelper(this);

        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        Button loginButton = (Button)findViewById(R.id.login);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("GOOD MORNING LIFE");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText)findViewById(R.id.username)).getText().toString();
                String password = ((EditText)findViewById(R.id.password)).getText().toString();

                Log.d("TAG", "LOGIN BUTTON CLICK");

                User u = new User(username);
                u.setPassword(password);
                db.addUser(u);
            }
        });

        Button updateButton = (Button)findViewById(R.id.update);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> allUsers = db.getAllUsers();
                String userText = "-----";
                for (User u : allUsers) {
                    u.setAge(40);
                    userText += u.toString();
                }
                db.updateUsers(allUsers);
                TextView text = (TextView)findViewById(R.id.text);
                changeText(text, userText);
            }
        });

    }

    public void changeText(TextView tv, String text)
    {tv.setText(text);}
}
