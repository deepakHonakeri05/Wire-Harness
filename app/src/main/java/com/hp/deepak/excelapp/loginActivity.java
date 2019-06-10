package com.hp.deepak.excelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

     Intent loginINT ;
    EditText userName,userPassword;
    String mUserName,mPassword;
    TextView invalidTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginINT = new Intent(this,main_task.class);

        userName = findViewById(R.id.usernameET);
        userPassword = findViewById(R.id.passwordET);
        invalidTV = findViewById(R.id.invalidTV);

        login();
    }

    public  void checkCredentials(){
        Log.e("Login Activity","Username : "+mUserName+"  Password : "+mPassword);

        if(mUserName.equals("user") && mPassword.equals("user")) {
            invalidTV.setVisibility(View.GONE);
            startActivity(loginINT);
        }
        else {
            invalidTV.setText("Invalid credentials");
        }
    }

    public void login()
    {

        Button loginBT = findViewById(R.id.custom_signin_button);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName =  userName.getText().toString() ;
                mPassword = userPassword.getText().toString();

                checkCredentials();

            }
        });

    }

}
