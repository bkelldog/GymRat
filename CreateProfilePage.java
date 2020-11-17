package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateProfilePage extends AppCompatActivity {

    private static final String TAG = "CreateProfilePage";

    //private DatabaseHelper db;

    //Profile data
    private String firstName = "", lastName = "";
    private String race = "";
    private int age = 0;
    private String gender = "";
    private String goals = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_page);

        /*try {
            db = (DatabaseHelper)getIntent().getExtras().getSerializable("DATABASEHELPER");
        } catch(Exception e){e.printStackTrace();}*/

        //Populate drop down boxes---------------------------------------------------------------------------------------
        //Race Field
        final Spinner raceField = (Spinner)findViewById(R.id.RaceField);
        String[] races = new String[] {"(None Selected)", "European", "Hispanic", "Middle Eastern", "African", "East Asian", "Pacific Islander", "Native American", "Other"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, races);
        raceField.setAdapter(arrayAdapter);
        raceField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String race = ((EditText)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // race = "";
            }
        });

        //Age Field
        final Spinner ageField = (Spinner)findViewById(R.id.AgeField);
        String[] ages = new String[71];
        ages[0] = "(Not Specified)";
        for (int i = 11; i < 81; i++)
            ages[i - 10] = "" + (i - 1);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        ageField.setAdapter(ageAdapter);
        ageField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // age = Integer.parseInt((String)ageField.getItemAtPosition(position));
                //db.addAge()
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // age = 0;
            }
        });

        //Gender Field
        final Spinner genderField = (Spinner)findViewById(R.id.GenderField);
        String[] genders = new String[] {"(None Selected)", "Male", "Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        genderField.setAdapter(genderAdapter);
        genderField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // gender = (String)genderField.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // gender = "";
            }
        });

        //Goals Field
        final Spinner goalsField = (Spinner)findViewById(R.id.GoalsField);
        String[] goals = new String[] {
                "(None Selected)",
                "Gain Muscle",
                "Lose Weight",
                "Bulk / Cut",
                "Get Toned",
                "Gain Endurance / Stamina",
                "Stay Fit"
        };
        ArrayAdapter<String> goalsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, goals);
        goalsField.setAdapter(goalsAdapter);
        goalsField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //goals = (String)goalsField.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // goals = "";
            }
        });


        //Handle click events for sign up button
        CardView signUpButton = (CardView)findViewById(R.id.SignUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomePageActivity();
            }
        });
    }

    private void startHomePageActivity()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    private boolean allFieldsValid()
    {
        EditText firstNameField = (EditText)findViewById(R.id.FirstNameField);
        EditText lastNameField = (EditText)findViewById(R.id.LastNameField);

        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();

        //Check first name
        if (firstName == null || firstName.equals(""))
        {
            firstNameField.setError("First name is required.");
            firstNameField.requestFocus();
            return false;
        }

        //Check last name
        if (lastName == null || lastName.equals(""))
        {
            lastNameField.setError("Last name is required.");
            lastNameField.requestFocus();
            return false;
        }

        return true;
    }
}