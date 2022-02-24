package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import vn.com.misa.cukcukstarterclone.R;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupIcon implements Parcelable {
    private String name;
    private boolean isSelected;

    public GroupIcon(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    protected GroupIcon(Parcel in) {
        name = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<GroupIcon> CREATOR = new Creator<GroupIcon>() {
        @Override
        public GroupIcon createFromParcel(Parcel in) {
            return new GroupIcon(in);
        }

        @Override
        public GroupIcon[] newArray(int size) {
            return new GroupIcon[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getDrawable(Context context) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(context.getString(R.string.prefix_drawable_name) + name, "drawable", context.getPackageName());
        return resources.getDrawable(resourceId);
    }
}
