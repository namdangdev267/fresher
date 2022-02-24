package vn.com.misa.cukcukstarterclone.ui.order.dto;

import android.os.Parcel;
import android.os.Parcelable;

import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class CartItemDto implements Parcelable {
    private final String id;
    private final String name;
    private final String itemId;
    private final int quantity;
    private final float price;

    public CartItemDto(MenuItem menuItem, CartItem cartItem) {
        this.id = cartItem.getId();
        this.name = menuItem.getName();
        this.itemId = cartItem.getProductId();
        this.price = cartItem.getPrice();
        this.quantity = cartItem.getQuantity();
    }

    protected CartItemDto(Parcel in) {
        id = in.readString();
        name = in.readString();
        itemId = in.readString();
        quantity = in.readInt();
        price = in.readFloat();
    }

    public static final Creator<CartItemDto> CREATOR = new Creator<CartItemDto>() {
        @Override
        public CartItemDto createFromParcel(Parcel in) {
            return new CartItemDto(in);
        }

        @Override
        public CartItemDto[] newArray(int size) {
            return new CartItemDto[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(itemId);
        parcel.writeInt(quantity);
        parcel.writeFloat(price);
    }
}
