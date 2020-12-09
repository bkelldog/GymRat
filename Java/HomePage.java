package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HOMEPAGE";

    private int userID = -1;
    private User u = null;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userID = getIntent().getIntExtra("userID", -1);
        Log.d(TAG, "USER ID: " + userID);

        db = new DatabaseHelper(this);

        for (User user : db.getAllUsers())
        {
            if (user.getID() == userID)
                u = user;
        }

        //Initialize Brother List listview
        if (u != null)
            fillBrotherList();

        // Handle Click Events on Chat Button object
        CardView chatButton = (CardView) findViewById(R.id.ChatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatActivity();
                Toast.makeText(HomePage.this, "Open Chat", Toast.LENGTH_SHORT).show();
            }
        });

        //Handle Click Events on Match Button Object
        CardView matchButton = (CardView) findViewById(R.id.MatchButton);
        matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchActivity();
            }
        });

        //Handle Click Events on Settings Button Object
        CardView settingsButton = (CardView) findViewById(R.id.SettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Open Settings", Toast.LENGTH_SHORT).show();
            }
        });

        //Handle Click Events on Edit Profile Button Object
        CardView editProfile = (CardView)findViewById(R.id.ProfileButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
    }

    private void fillBrotherList()
    {
        ListView listView = (ListView) findViewById(R.id.BrothersList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, u.getBrothersList());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomePage.this, "Clicked Item: " + position + " " + u.getBrothersList().get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void openMatchActivity()
    {
        //Intent intent = new Intent(this, MatchPage.class);
        //startActivity(intent);
    }

    private void openProfileActivity()
    {
        Intent intent = new Intent(this, ProfilePage.class);
        //intent.putExtra("userID", userID);
        startActivity(intent);
    }

    private void openChatActivity()
    {
        //Intent intent = new Intent(this, ChatPage.class);
        //startActivity(intent);
    }
}
