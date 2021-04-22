package com.example.ragister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextInputLayout regNumber, regUsername, regName, regEmail, regPassword;
    Button regToLoginBtn;
    ImageButton regButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);


        regName = findViewById(R.id.fullname);
        regUsername = findViewById(R.id.Username);
        regNumber = findViewById(R.id.Number);
        regEmail = findViewById(R.id.Email);
        regPassword = findViewById(R.id.password);
        regButton = findViewById(R.id.signupb);
        regToLoginBtn  = findViewById(R.id.signIN);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //boolen
                if (!validateEmail() | !validatePassword() | !validateUsername() | !validateName() | !validatePhoneNo()){
                    return;
                }

                //get all values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneno = regNumber.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();


                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneno, password);

                reference.child(phoneno).setValue(helperClass);

            }
        });








    }


    //validation form
    private Boolean validateName () {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail () {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)){
            regEmail.setError("Invalid email");
            return false;
        }
        else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo () {
        String val = regNumber.getEditText().getText().toString();
        if (val.isEmpty()){
            regNumber.setError("Field cannot be empty");
            return false;
        }

        else {
            regNumber.setError(null);
            return true;
        }
    }

    private Boolean validatePassword () {
        String val = regPassword.getEditText().getText().toString();
        if (val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }

        else {
            regPassword.setError(null);
            return true;
        }
    }

    private Boolean validateUsername () {
        String val = regUsername.getEditText().getText().toString();
        if (val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }

        else {
            regUsername.setError(null);
            return true;
        }
    }



}