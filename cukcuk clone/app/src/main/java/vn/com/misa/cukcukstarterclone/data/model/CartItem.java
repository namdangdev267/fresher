package vn.com.misa.cukcukstarterclone.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích class: Lớp biểu diễn Item trong giỏ hàng
 * - Gồm các thuộc tính:
 * id: Unique id của từng item
 * quantity: Số lượng sản phẩm
 * amount: Lượng tiền phải trả
 * price: Giá tính theo giá niêm yết
 * details: Ghi chú
 * productId: id tham chiếu tới sản phẩm
 * cartId: id tham chiếu tới giỏ hàng
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class CartItem implements Parcelable {

    public static final String TABLE_NAME = "CART_ITEM";

    public static final String ID = "ID";
    public static final String QUANTITY = "QUANTITY";
    public static final String AMOUNT = "AMOUNT";
    public static final String PRICE = "PRICE";
    public static final String DETAILS = "DETAILS";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String CART_ID = "CART_ID";

    private String id;
    private int quantity;
    private float amount;
    private float price;
    private String details;
    private String productId;
    private String cartId;

    public CartItem(int quantity, float amount, float price, String details, String productId, String cartId) {
        this.id = UUID.randomUUID().toString();
        this.quantity = quantity;
        this.amount = amount;
        this.price = price;
        this.details = details;
        this.productId = productId;
        this.cartId = cartId;
    }

    public CartItem(Cursor cursor) {
        try {
            this.id = cursor.getString(cursor.getColumnIndex(ID));
            this.quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
            this.amount = cursor.getFloat(cursor.getColumnIndex(AMOUNT));
            this.price = cursor.getFloat(cursor.getColumnIndex(PRICE));
            this.details = cursor.getString(cursor.getColumnIndex(DETAILS));
            this.productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            this.cartId = cursor.getString(cursor.getColumnIndex(CART_ID));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    protected CartItem(Parcel in) {
        id = in.readString();
        quantity = in.readInt();
        amount = in.readFloat();
        price = in.readFloat();
        details = in.readString();
        productId = in.readString();
        cartId = in.readString();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(QUANTITY, quantity);
        contentValues.put(AMOUNT, amount);
        contentValues.put(PRICE, price);
        contentValues.put(DETAILS, details);
        contentValues.put(PRODUCT_ID, productId);
        contentValues.put(CART_ID, cartId);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeInt(quantity);
        parcel.writeFloat(amount);
        parcel.writeFloat(price);
        parcel.writeString(details);
        parcel.writeString(productId);
        parcel.writeString(cartId);
    }
}
