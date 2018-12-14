package com.plantatree.plantatree;

public class ShoppingCartEntry {
    
    private Catalogue_Product CatalogProduct;
    private int productQuantity;

    public ShoppingCartEntry(Catalogue_Product catalogProduct, int quantity) {
        CatalogProduct = catalogProduct;
        productQuantity = quantity;
    }

    public int getQuantity() {
        return productQuantity;
    }

    public void setQuantity(int quantity) {
        productQuantity = quantity;
    }

}
