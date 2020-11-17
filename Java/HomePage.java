package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    //private final String userID = getIntent().getStringExtra("userID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //Add elements to the Scroll ListView
        ListView listView = (ListView) findViewById(R.id.BrothersList);
        final ArrayList<String> elements = new ArrayList<String>();
        elements.add("Brother 1");
        elements.add("Brother 2");
        elements.add("Brother 3");
        elements.add("Brother 4");
        elements.add("Brother 5");
        elements.add("Brother 6");
        elements.add("Brother 7");
        elements.add("Brother 8");
        elements.add("Brother 1");
        elements.add("Brother 2");
        elements.add("Brother 3");
        elements.add("Brother 4");
        elements.add("Brother 5");
        elements.add("Brother 6");
        elements.add("Brother 7");
        elements.add("Brother 8");
        elements.add("Brother 1");
        elements.add("Brother 2");
        elements.add("Brother 3");
        elements.add("Brother 4");
        elements.add("Brother 5");
        elements.add("Brother 6");
        elements.add("Brother 7");
        elements.add("Brother 8");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, elements);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomePage.this, "Clicked Item: " + position + " " + elements.get(position).toString(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

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
