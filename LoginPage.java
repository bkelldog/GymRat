package com.example.gymrattrial3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginPage extends AppCompatActivity {

    /*
    This class will make sure the user is registered in the GymRat database before allowing him to
    enter the app. There will be two databases which store user data: one in the cloud on Firebase,
    the other stored locally through SQLite. The program will first check the user's data against
    the local SQLite database, to see if he has already registered on this device before. The only
    way his data would be stored locally is if his account is verified in the Firebase database. If
    he has not logged in on this device before, he will be checked against the Firebase data. If
    his data is not there either, he cannot log in (i.e. he does not have an account).
     */

    private static final String TAG = "LoginPage";

    FirebaseAuth auth;
    private String userID;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        CardView loginButton = (CardView)findViewById(R.id.LoginButton);

        Log.d(TAG, "Login created");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText)findViewById(R.id.editEmail)).getText().toString();
                String password = ((EditText)findViewById(R.id.editPassword)).getText().toString();

                if (validate(email, password))
                    startHomePageActivity();
            }
        });
    }

    private boolean validate(String email, String password)
    {
        //if (!allFieldsValid())
        //    return false;
        ((ProgressBar) findViewById(R.id.LoginProgressBar)).setVisibility(View.VISIBLE);
        Log.d(TAG, "IN VALIDATE");

        //First, validate against SQLite database
        db = new DatabaseHelper(this);
        EditText emailField = (EditText)findViewById(R.id.editEmail);
        if (emailField.getText().toString() == null)
            return false;
        db.addUser(new User(emailField.getText().toString()));
        Log.d(TAG, db.getAllUsers().get(0).toString());

return true;
        /*if (db.getItemIDByEmail(email) != null)
        {
            if (db.getItemIDByPassword(password) != null)
            {
                return true;
            }
        }
        System.out.println("GOT TO Firebase");
        //If SQLite gives no result, validate against Firebase
        auth = FirebaseAuth.getInstance();

        final TaskCompletionSource<Boolean> tasks = new TaskCompletionSource<>();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ((ProgressBar) findViewById(R.id.LoginProgressBar)).setVisibility(View.INVISIBLE);
                    System.out.println("GOT TO FIREBASE COMPLETE");
                    tasks.setResult(true);
                } else {
                    Toast.makeText(LoginPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    ((ProgressBar) findViewById(R.id.SignUpProgressBar)).setVisibility(View.INVISIBLE);
                    tasks.setResult(false);
                    System.out.println("GOT TO FIREBASE FAILURE");
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(LoginPage.this, "Failed Registration" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("SignUpPage", "Failed Registration", e);
                }
            }
        });

        return tasks.getTask().getResult();*/
    }

    private void startHomePageActivity()
    {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    public boolean allFieldsValid()
    {
        EditText emailField = (EditText)findViewById(R.id.editEmail);
        EditText passwordField = (EditText)findViewById(R.id.editPassword);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email == null || email.equals(""))
        {
            emailField.setError("Email is required.");
            emailField.requestFocus();
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailField.setError("Please provide valid email address.");
            emailField.requestFocus();
            return false;
        }

        if (password == null || password.equals(""))
        {
            passwordField.setError("Password is required.");
            passwordField.requestFocus();
            return false;
        }

        return true;
    }
}