package vn.com.misa.cukcukstarterclone.ui.main.addorder.dto;

import android.os.Parcel;
import android.os.Parcelable;

import vn.com.misa.cukcukstarterclone.data.model.CartItem;

/**
 * - Mục đích Class: Lớp biểu diễn CartItem để hiển thị lên View
 *
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class CartItemDto implements Parcelable {
    private final String id;
    private String name;
    private String itemId;
    private float price;
    private float discount;
    private int quantity;

    public CartItemDto(CartItem item) {
        this.id = item.getId();
        this.name = item.getProductId();
        this.itemId = item.getProductId();
        this.price = item.getPrice();
        this.discount = 0;
        this.quantity = item.getQuantity();
    }

    protected CartItemDto(Parcel in) {
        id = in.readString();
        name = in.readString();
        itemId = in.readString();
        price = in.readFloat();
        discount = in.readFloat();
        quantity = in.readInt();
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

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        parcel.writeFloat(price);
        parcel.writeFloat(discount);
        parcel.writeInt(quantity);
    }
}
