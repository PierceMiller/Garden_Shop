package com.plantatree.plantatree;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream53.plantatree.plantatree.R;

import java.util.List;

public class Catalogue_Adapter extends BaseAdapter {

    private List<Catalogue_Product> productList;
    private LayoutInflater mInflater;
    private boolean mShowQuantity;

    public Catalogue_Adapter(List<Catalogue_Product> list, LayoutInflater inflater, boolean showQuantity) {

        productList = list;
        mInflater = inflater;
        mShowQuantity = showQuantity;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewItem item;

        if (convertView == null) {

            /*Reads the activity 'item' and assigns the layout
            * and its corresponding variables/ids to the text/image/quantity*/
            convertView = mInflater.inflate(R.layout.item, null);
            item = new ViewItem();

            item.productImageView = (ImageView) convertView.findViewById(R.id.ImageViewItem);
            item.productTitle = (TextView) convertView.findViewById(R.id.TextViewItem);
            item.productQuantity = (TextView) convertView.findViewById(R.id.textViewQuantity);

            //sets an argument being the values of the ViewItem 'item'
            convertView.setTag(item);
        } else {
            //retrieves the value of the object
            item = (ViewItem) convertView.getTag();
        }

        Catalogue_Product curProduct = productList.get(position);

        //sets the image to the ImageView, dependent on the users choice
        item.productImageView.setImageDrawable(curProduct.productImage);

        //sets the title to the TextView title, dependent on the users choice
        item.productTitle.setText(curProduct.title);

        //Displays the quantity of items within the cart, which retrieves the current cart entry
        if (mShowQuantity) {

            item.productQuantity.setTextColor(Color.RED);
            item.productQuantity.setText("Quantity: " + Catalogue_Helper.getProductQuantity(curProduct));

        } else {
            // Hid the view
            item.productQuantity.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewItem {

        ImageView productImageView;
        TextView productTitle;
        TextView productQuantity;
    }
}