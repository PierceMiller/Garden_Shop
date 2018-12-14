package com.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.stream53.plantatree.plantatree.R;

public class Pay_Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        /*final Button buttonPlaceOrder = (Button) findViewById(R.id.placeOrder);
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PlaceOrder();
            }
        });*/

        final Button buttonBackToShipping = (Button) findViewById(R.id.backtoShippingDetails);
        buttonBackToShipping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BackToShipping();
            }
        });
    }

    public void PlaceOrder() {
        //button that directs user to order confirmation page
        Intent intent = new Intent(Pay_Payment.this, Pay_Confirmation.class);
        startActivity(intent);
    }

    public void BackToShipping() {
        Intent intent = new Intent(Pay_Payment.this, Pay_Shipping.class);
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
