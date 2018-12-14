package com.plantatree.plantatree;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stream53.plantatree.plantatree.R;

public class Catalogue_Details extends AppCompatActivity {

    /*TODO: Menu needs to be its own class, to prevent repeated code?*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_Catalogue) {

            Intent startTopic1 = new Intent(this, Catalogue_Activity.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Cart) {

            Intent startTopic1 = new Intent(this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Quiz) {

            Intent startTopic1 = new Intent(this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Compare) {

            Intent startTopic1 = new Intent(this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_description);

        List<Catalogue_Product> catalog = Catalogue_Helper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(Catalogue_Helper.PRODUCT_INDEX);
        final Catalogue_Product selectedProduct = catalog.get(productIndex);

        // Set the proper image and text
        ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);
        productImageView.setImageDrawable(selectedProduct.productImage);

        TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
        productTitleTextView.setText(selectedProduct.title);

        TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);
        productDetailsTextView.setText(selectedProduct.description);

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewProductPrice);
        productPriceTextView.setText("$" + selectedProduct.price);

        // Update the current quantity in the cart
        TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
        textViewCurrentQuantity.setText("Currently in Cart: "
                + Catalogue_Helper.getProductQuantity(selectedProduct));

        // Save a reference to the quantity edit text
        final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
        addToCartButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Check to see that a valid quantity was entered
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editTextQuantity.getText()
                            .toString());

                    if (quantity < 0) {
                        Toast.makeText(getBaseContext(),
                                "Please enter a quantity of 0 or higher",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),
                            "Please enter a numeric quantity",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                // If we make it here, a valid quantity was entered
                Catalogue_Helper.setQuantity(selectedProduct, quantity);

                // Close the activity
                finish();
            }
        });
    }

}


