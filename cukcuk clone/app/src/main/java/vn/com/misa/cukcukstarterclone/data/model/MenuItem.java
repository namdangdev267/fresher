package vn.com.misa.cukcukstarterclone.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * - Mục đích class: Lớp biểu diễn Sản phẩm
 * - Gồm các thuộc tính:
 * id: Unique id cho từng sản phẩm
 * price: Giá niêm yết của sản phẩm
 * imageUrl: Hình ảnh đại diện cho sản phẩm
 * name: Tên sản phẩm
 * unit: Đơn vị tính
 * groupId: id tham chiếu tới Nhóm sản phẩm
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class MenuItem implements Parcelable {

    public static final String TABLE_NAME = "MENU_ITEM";
    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String IMAGE_URL = "imageUrl";
    public static final String NAME = "name";
    public static final String UNIT = "unit";
    public static final String GROUP_ID = "groupId";

    private final String id;
    private float price;
    private String imageUrl;
    private String name;
    private String unit;
    private String groupId;

    public MenuItem() {
        this.id = UUID.randomUUID().toString();
        this.imageUrl = "";
    }

    public MenuItem(float price, String imageUrl, String name, String unit, String groupId) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
        this.unit = unit;
        this.groupId = groupId;
    }

    public MenuItem(String id, float price, String imageUrl, String name, String unit, String groupId) {
        this.id = id;
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
        this.unit = unit;
        this.groupId = groupId;
    }

    public MenuItem(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex(ID));
        this.price = cursor.getFloat(cursor.getColumnIndex(PRICE));
        this.imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_URL));
        this.name = cursor.getString(cursor.getColumnIndex(NAME));
        this.unit = cursor.getString(cursor.getColumnIndex(UNIT));
        this.groupId = cursor.getString(cursor.getColumnIndex(GROUP_ID));
    }

    protected MenuItem(Parcel in) {
        id = in.readString();
        price = in.readFloat();
        imageUrl = in.readString();
        name = in.readString();
        unit = in.readString();
        groupId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeFloat(price);
        dest.writeString(imageUrl);
        dest.writeString(name);
        dest.writeString(unit);
        dest.writeString(groupId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, getId());
        contentValues.put(PRICE, getPrice());
        contentValues.put(IMAGE_URL, getImageUrl());
        contentValues.put(NAME, getName());
        contentValues.put(UNIT, getUnit());
        contentValues.put(GROUP_ID, getGroupId());

        return contentValues;
    }
}
