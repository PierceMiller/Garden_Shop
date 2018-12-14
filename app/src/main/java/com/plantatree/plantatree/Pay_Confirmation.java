package com.plantatree.plantatree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.stream53.plantatree.plantatree.R;

public class Pay_Confirmation extends AppCompatActivity {

    EditText FIRST_NAME, lastName, emailName;
    TextView f_NAME, lastNameView, emailNameView, subTotalView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        FIRST_NAME = (EditText) findViewById(R.id.firstName);
        f_NAME = (TextView) findViewById(R.id.displayFirstName);

        String name = FIRST_NAME.getText().toString();
        f_NAME.setText(name);

        //PLACE value into displayFirstName textview

        /*
        //GET variable firstName value
        firstName =(EditText)findViewById(R.id.firstName);
        String fName = firstName.getText().toString();

        //PLACE value into displayFirstName textview
        firstNameView = (TextView) findViewById(R.id.displayFirstName);
        firstNameView.setText(fName);
        */

        /*
        //GET
        lastName =(EditText)findViewById(R.id.lastName);
        String lName = lastName.getText().toString();
        //PLACE
        lastNameView = (TextView) findViewById(R.id.displayLastName);
        lastNameView.setText(lName);

        //GET
        emailName =(EditText)findViewById(R.id.email);
        String eName = emailName.getText().toString();
        //PLACE
        emailNameView = (TextView) findViewById(R.id.displayEmail);
        emailNameView.setText(eName);

        //GET
        Shopping_Cart cart = new Shopping_Cart();
        TextView priceText = cart.productPriceTextView;
        //PLACE
        subTotalView = (TextView) findViewById(R.id.displaySubTotal);
        subTotalView.setText(priceText.toString());

        */


    }

    @Override
    protected void onResume() {
        super.onResume();





    }

}


