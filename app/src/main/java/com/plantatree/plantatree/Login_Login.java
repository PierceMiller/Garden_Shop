package com.plantatree.plantatree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stream53.plantatree.plantatree.R;

public class Login_Login extends AppCompatActivity {

    EditText EMAIL_LOGIN, PASS_LOGIN;
    Button LOGIN;
    DBHelper_Login DATABASE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DATABASE = new DBHelper_Login(this);
        EMAIL_LOGIN = (EditText)findViewById(R.id.edit_text_emailLogin);
        PASS_LOGIN = (EditText)findViewById(R.id.edit_text_loginPass);
        LOGIN = (Button)findViewById(R.id.button_login);

        //LOGIN BUTTON, ON LOGIN ACTIVITY LISTENER
        LOGIN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = EMAIL_LOGIN.getText().toString();
                String password = PASS_LOGIN.getText().toString();
                Boolean validate = DATABASE.emailPassword(email, password);

                if(validate==true){

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    redirect();

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong  Email or Passowrd", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void redirect(){

        Intent intent = new Intent(this, Catalogue_Activity.class);
        startActivity(intent);
    }
}
