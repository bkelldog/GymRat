package com.example.gymrattrial3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /*
    This class will provide the user with two options: login or sign up.
    There will be two buttons, and the GymRat logo on top.
    Program will listen for the user to select either button, then open the corresponding activity.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView loginButton = (CardView) findViewById(R.id.LoginButton);
        CardView signUpButton = (CardView) findViewById(R.id.SignUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignUpPage();
            }
        });
    }

    private void startLoginPage()
    {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    private void startSignUpPage()
    {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }
}