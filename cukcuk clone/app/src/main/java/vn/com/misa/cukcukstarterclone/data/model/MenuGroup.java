package vn.com.misa.cukcukstarterclone.data.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import vn.com.misa.cukcukstarterclone.R;

/**
 * - Mục đích class: Lớp biểu diễn Nhóm sản phẩm
 * - Gồm các thuộc tính:
 * id: Unique id cho nhóm sản phẩm
 * title: Tên nhóm
 * iconUrl: Tên của icon hiển thị của nhóml
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class MenuGroup implements Parcelable {

    public static final String TABLE_NAME = "MENU_GROUP";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ICON_URL = "iconUrl";

    private final String id;
    private String title;
    private String iconUrl;

    public MenuGroup(String title, String iconUrl) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.iconUrl = iconUrl;
    }

    public MenuGroup(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex(ID));
        this.title = cursor.getString(cursor.getColumnIndex(TITLE));
        this.iconUrl = cursor.getString(cursor.getColumnIndex(ICON_URL));
    }

    protected MenuGroup(Parcel in) {
        id = in.readString();
        title = in.readString();
        iconUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(iconUrl);
    }

    public static final Creator<MenuGroup> CREATOR = new Creator<MenuGroup>() {
        @Override
        public MenuGroup createFromParcel(Parcel in) {
            return new MenuGroup(in);
        }

        @Override
        public MenuGroup[] newArray(int size) {
            return new MenuGroup[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getDrawable(Context context) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(context.getString(R.string.prefix_drawable_name) + iconUrl, "drawable", context.getPackageName());
        return resources.getDrawable(resourceId);
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, getId());
        contentValues.put(TITLE, getTitle());
        contentValues.put(ICON_URL, getIconUrl());

        return contentValues;
    }
}
