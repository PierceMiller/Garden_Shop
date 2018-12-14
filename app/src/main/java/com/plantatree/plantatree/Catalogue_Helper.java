package com.plantatree.plantatree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

import com.stream53.plantatree.plantatree.R;

public class Catalogue_Helper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
    private static List<Catalogue_Product> catalog;
    private static Map<Catalogue_Product, ShoppingCartEntry> cartMap = new HashMap<Catalogue_Product, ShoppingCartEntry>();

    public static List<Catalogue_Product> getCatalog(Resources res){

        if(catalog == null) {

            catalog = new Vector<Catalogue_Product>();

            catalog.add(new Catalogue_Product("Palm Tree", res
                    .getDrawable(R.drawable.palm_tree),
                    "The Arecaceae are a botanical family of perennial plants. Their growth form can be climbers, shrubs, trees and stemless plants, all commonly known as palms. Those having a tree form are colloquially called palm trees." + System.getProperty("line.separator")  +
                            " " + System.getProperty("line.separator") +
                            "Category: Palm Tree" + System.getProperty("line.separator") +
                            "Soil Drainage: Fast" + System.getProperty("line.separator") +
                            "Sun: Sunny " + System.getProperty("line.separator")+
                            "Maintenance requirements: Medium " + System.getProperty("line.separator") +
                            "Max height of mature tree: over 3m " + System.getProperty("line.separator") +
                            "Growth rate: Fast", 29.99));

            catalog.add(new Catalogue_Product("Oak Tree", res
                    .getDrawable(R.drawable.oak_tree),
                    "An oak is a tree or shrub in the genus Quercus of the beech family, Fagaceae. There are approximately 600 extant species of oaks. The common name \"oak\" also appears in the names of species in related genera, notably Lithocarpus, as well as in those of unrelated species such as Grevillea robusta and the Casuarinaceae." + System.getProperty("line.separator") +
                            " " + System.getProperty("line.separator") +
                            "Category: Evengreen" + System.getProperty("line.separator") +
                            "Soil Drainage: Fast" + System.getProperty("line.separator") +
                            "Sun: Sunny " + System.getProperty("line.separator")+
                            "Maintenance requirements: Low" + System.getProperty("line.separator") +
                            "Max height of mature tree: over 3m " + System.getProperty("line.separator") +
                            "Growth rate: Medium", 74.99));

            catalog.add(new Catalogue_Product("Kauri Tree", res
                    .getDrawable(R.drawable.kauri_tree),
                    "Agathis australis, commonly known by its Māori name kauri, is a coniferous tree of Araucariaceae in the genus Agathis, found north of 38°S in the northern districts of New Zealand's North Island." + System.getProperty("line.separator") +
                            " " + System.getProperty("line.separator") +
                            "Category: Hardwood" + System.getProperty("line.separator") +
                            "Soil Drainage: Low" + System.getProperty("line.separator") +
                            "Sun: Medium " + System.getProperty("line.separator")+
                            "Maintenance requirements: Low " + System.getProperty("line.separator") +
                            "Max height of mature tree: over 3m " + System.getProperty("line.separator") +
                            "Growth rate: Medium", 154.99));

            catalog.add(new Catalogue_Product("Bay Tree", res
                    .getDrawable(R.drawable.bay_tree),
                    "The bay tree is a popular evergreen shrub suitable for containers or growing in the ground." + System.getProperty("line.separator") +
                            " " + System.getProperty("line.separator") +
                            "Category: Shurb" + System.getProperty("line.separator") +
                            "Soil Drainage: Medium" + System.getProperty("line.separator") +
                            "Sun: Sunny " + System.getProperty("line.separator")+
                            "Maintenance requirements: High " + System.getProperty("line.separator") +
                            "Max height of mature tree: over 3m " + System.getProperty("line.separator") +
                            "Growth rate: Slow", 12.99));

            catalog.add(new Catalogue_Product("Cabbage Tree", res
                    .getDrawable(R.drawable.cabbage_tree),
                    "Cordyline australis, commonly known as the cabbage tree, cabbage-palm or tī kōuka, is a widely branched monocot tree endemic to New Zealand. It grows up to 20 metres tall with a stout trunk and sword-like leaves, which are clustered at the tips of the branches and can be up to 1 metre long." + System.getProperty("line.separator") +
                            " " + System.getProperty("line.separator") +
                            "Category: Gum Tree" + System.getProperty("line.separator") +
                            "Soil Drainage: Medium" + System.getProperty("line.separator") +
                            "Sun: Sunny " + System.getProperty("line.separator")+
                            "Maintenance requirements: Medium " + System.getProperty("line.separator") +
                            "Max height of mature tree: over 3m " + System.getProperty("line.separator") +
                            "Growth rate: Medium", 8.99));

            catalog.add(new Catalogue_Product("Plant pot", res
                    .getDrawable(R.drawable.plant_pot),
                    "Plant pot is a container in which flowers and other plants are cultivated and displayed", 4.99));

            catalog.add(new Catalogue_Product("Spade", res
                    .getDrawable(R.drawable.spade),
                    "A tool with a sharp-edged, typically rectangular, metal blade and a long handle, used for digging or cutting earth, sand, turf, etc.", 24.99));

            catalog.add(new Catalogue_Product("Compost", res
                    .getDrawable(R.drawable.compost),
                    "A tool with a sharp-edged, typically rectangular, metal blade and a long handle, used for digging or cutting earth, sand, turf, etc.", 13.99));

        }

        return catalog;
    }

    public static void setQuantity(Catalogue_Product catalogProduct, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(catalogProduct);

        // If the quantity is zero or less, remove the item from the cart
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(catalogProduct);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(catalogProduct, quantity);
            cartMap.put(catalogProduct, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Catalogue_Product catalogProduct) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(catalogProduct);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Catalogue_Product catalogProduct) {
        cartMap.remove(catalogProduct);
    }

    public static List<Catalogue_Product> getCartList() {

        List<Catalogue_Product> cartList = new Vector<Catalogue_Product>(cartMap.keySet().size());
        for(Catalogue_Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}