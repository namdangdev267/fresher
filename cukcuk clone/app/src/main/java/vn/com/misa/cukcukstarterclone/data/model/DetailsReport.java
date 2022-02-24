package vn.com.misa.cukcukstarterclone.data.model;

import android.database.Cursor;

import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 03-Feb-2021.
 */
public class DetailsReport {

    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
    public static final String AMOUNT = "amount";
    public static final String PRICE = "price";
    public static final String DISCOUNT = "discount";

    private String name;
    private int quantity;
    private float amount;
    private float price;
    private float discount;

    public DetailsReport(Cursor cursor) {
        try {
            this.name = cursor.getString(cursor.getColumnIndex(NAME));
            this.quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
            this.amount = cursor.getFloat(cursor.getColumnIndex(AMOUNT));
            this.price = cursor.getFloat(cursor.getColumnIndex(PRICE));
            this.discount = cursor.getFloat(cursor.getColumnIndex(DISCOUNT));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }
}
