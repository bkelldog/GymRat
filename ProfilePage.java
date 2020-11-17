package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    private final String userID = getIntent().getStringExtra("userID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Handle Back Button Clicks
        CardView backButton = (CardView)findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomePage();
            }
        });

        //Handle Account Data Button Clicks
        CardView accountPanel = (CardView)findViewById(R.id.AccountPanel);
        accountPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Add elements to the Brothers ListView
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
                Toast.makeText(ProfilePage.this, "Clicked Item: " + position + " " + elements.get(position).toString(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        populatePRList();
    }

    private void startHomePage()
    {
        onBackPressed();
    }

    //Method will search for PR data is SQLite Database, and either add to listView, or add values of 0.
    private void populatePRList()
    {
        //1. Check for data in SQLite database

        //2. If data is there, add to list.

        //3. If no data is there, add all 0s to list
    }
}