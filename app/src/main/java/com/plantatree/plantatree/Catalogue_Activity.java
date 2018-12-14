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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.stream53.plantatree.plantatree.R;

public class Catalogue_Activity extends AppCompatActivity {

    private List<Catalogue_Product> PRODUCT_LIST;
    private Catalogue_Adapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*Inflater variable, reads the chosen xml file and
        * creates the corresponding objects to it*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /*Redirects the user depending where they are wanting to go
        * e.g Catalogue triggers, redirects the activity the catalog_activity
        * where the elected layout is displayed for that class*/

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

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Will be created when class is called upon first.
        setContentView(R.layout.activity_catalogue);

        //References an object from the product catalog and assigns to a variable
        PRODUCT_LIST = Catalogue_Helper.getCatalog(getResources());

        // Create a list
        ListView lv = (ListView) findViewById(R.id.ListViewCatalog);

        adapter = new Catalogue_Adapter(PRODUCT_LIST, getLayoutInflater(), false);
        lv.setAdapter(adapter);

        //FILTER DOES NOT WORK
        /*EditText filter = (EditText) findViewById(R.id.edit_text_search);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                (Catalogue_Activity.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Retrieves the chosen product and redirects
                the user to the detail of that product*/
                Intent intent = new Intent(getBaseContext(),Catalogue_Details.class);

                /*TODO: FIX BUG (Sends user to the wrong product details when in cart) */
                intent.putExtra(Catalogue_Helper.PRODUCT_INDEX, position);
                startActivity(intent);
            }
        });

        //Provides the ability to view the cart within the chosen product details
        Button viewShoppingCart = (Button) findViewById(R.id.button_view_cart);
        viewShoppingCart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent viewShoppingCartIntent = new Intent(getBaseContext(), Shopping_Cart.class);
                startActivity(viewShoppingCartIntent);
            }
        });
    }
}
