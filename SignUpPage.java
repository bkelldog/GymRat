package com.example.gymrattrial3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignUpPage extends AppCompatActivity {

    FirebaseAuth auth;
    private static final String TAG = "SignUpPage";

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        auth = FirebaseAuth.getInstance();
        db = new DatabaseHelper(this);

        CardView signUpButton = (CardView)findViewById(R.id.SignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();

                String firstName = ((EditText)findViewById(R.id.FirstNameField)).getText().toString();
                u.setName(firstName);

                //DELETE AFTER TESTING
                startCreateProfilePage();


                /*if (allFieldsValid())
                {
                    String email = ((EditText)findViewById(R.id.EmailField)).getText().toString();
                    String password = ((EditText)findViewById(R.id.PasswordField)).getText().toString();

                    ((ProgressBar)findViewById(R.id.SignUpProgressBar)).setVisibility(View.VISIBLE);

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                ((ProgressBar)findViewById(R.id.SignUpProgressBar)).setVisibility(View.INVISIBLE);
                                startCreateProfilePage();
                            }
                            else {
                                Toast.makeText(SignUpPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                ((ProgressBar)findViewById(R.id.SignUpProgressBar)).setVisibility(View.INVISIBLE);

                                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                Toast.makeText(SignUpPage.this, "Failed Registration" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("SignUpPage", "Failed Registration", e);
                            }
                        }
                    });


                    String firstName = ((EditText)findViewById(R.id.FirstNameField)).getText().toString();
                    String lastName = ((EditText)findViewById(R.id.LastNameField)).getText().toString();

                    db.addFirstName(firstName);
                    db.addLastName(lastName);
                }*/
            }
        });
    }

    public void startCreateProfilePage()
    {
        Intent intent = new Intent(this, CreateProfilePage.class);
        //intent.putExtras("DATABASEHELPER", db);
        startActivity(intent);
    }

    private boolean allFieldsValid()
    {
        EditText firstNameField = (EditText)findViewById(R.id.FirstNameField);
        EditText lastNameField = (EditText)findViewById(R.id.LastNameField);
        EditText emailField = (EditText)findViewById(R.id.EmailField);
        EditText password1Field = (EditText)findViewById(R.id.PasswordField);
        EditText password2Field = (EditText)findViewById(R.id.ConfirmPasswordField);

        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = emailField.getText().toString();
        String password1 = password1Field.getText().toString();
        String password2 = password2Field.getText().toString();

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

        //Check email
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

        if (password1 == null || password1.equals("") || password2 == null || password2.equals(""))
        {
            if (!password1.equals(password2))
            {
                password2Field.setError("Passwords must match.");
                password2Field.requestFocus();
                return false;
            }

            if (password1.length() < 6 || password2.length() < 6)
            {
                password2Field.setError("Password must be at least 6 characters.");
                password2Field.requestFocus();
                return false;
            }
            password2Field.setError("Password is required.");
            password2Field.requestFocus();
            return false;
        }

        return true;
    }

    /*public void addData(String newEntry) {
        boolean insertData = db.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }*/

    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}