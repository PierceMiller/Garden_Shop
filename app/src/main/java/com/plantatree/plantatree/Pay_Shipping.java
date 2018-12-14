package com.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stream53.plantatree.plantatree.R;

public class Pay_Shipping extends AppCompatActivity {

    EditText firstName, lastName, emailName;
    String fName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        final Button buttonShipping = findViewById(R.id.continueToBilling);
        buttonShipping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                redirect();
            }
        });

        final Button buttonBackToCart = findViewById(R.id.backToViewCart);
        buttonBackToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                redirect2();
            }
        });


        //first name
        firstName =(EditText)findViewById(R.id.firstName);
        fName = firstName.getText().toString();

        /*
        //last name
        lastName =(EditText)findViewById(R.id.lastName);
        String lName = lastName.getText().toString();

        //email
        emailName =(EditText)findViewById(R.id.email);
        String eName = emailName.getText().toString();

        //Grab price from shopping_cart subtotal
        Shopping_Cart cart = new Shopping_Cart();
        TextView priceText = cart.productPriceTextView;

        String priceString = priceText.getText().toString();
        */


    }

    public void redirect(){

        //redirects the start activity to the quiz.
        Intent intent = new Intent(this, Pay_Payment.class);
        startActivity(intent);
    }

    public void redirect2() {
        Intent intent = new Intent(this, Shopping_Cart.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_Catalogue){

            Intent startTopic1 = new Intent (this, Catalogue_Activity.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Quiz){

            Intent startTopic1 = new Intent (this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Compare){

            Intent startTopic1 = new Intent (this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }
}
