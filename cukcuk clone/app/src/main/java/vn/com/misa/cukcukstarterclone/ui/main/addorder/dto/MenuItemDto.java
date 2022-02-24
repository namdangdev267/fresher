package vn.com.misa.cukcukstarterclone.ui.main.addorder.dto;

import android.os.Parcel;
import android.os.Parcelable;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: DTO Class để hiển thị lên View cho {@link MenuItem}
 *
 * @created_by KhanhNQ on 26-Jan-21.
 */
public class MenuItemDto implements Parcelable {
    private final String id;
    private final float price;
    private final String imageUrl;
    private final String name;
    private final String groupId;
    private int quantity;

    public MenuItemDto(MenuItem item, int quantity) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.imageUrl = item.getImageUrl();
        this.name = item.getName();
        this.groupId = item.getGroupId();
        this.quantity = quantity;
    }

    protected MenuItemDto(Parcel in) {
        id = in.readString();
        price = in.readFloat();
        imageUrl = in.readString();
        name = in.readString();
        groupId = in.readString();
        quantity = in.readInt();
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static final Creator<MenuItemDto> CREATOR = new Creator<MenuItemDto>() {
        @Override
        public MenuItemDto createFromParcel(Parcel in) {
            return new MenuItemDto(in);
        }

        @Override
        public MenuItemDto[] newArray(int size) {
            return new MenuItemDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeFloat(price);
        dest.writeString(imageUrl);
        dest.writeString(name);
        dest.writeString(groupId);
        dest.writeInt(quantity);
    }
}
