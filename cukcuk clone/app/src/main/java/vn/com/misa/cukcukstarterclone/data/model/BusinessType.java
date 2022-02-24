package vn.com.misa.cukcukstarterclone.data.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

/**
 * - Mục đích class: Lớp biểu diễn loại của nhà hàng
 * - Gồm có các thuộc tính:
 * id: Unique id cho từng loại
 * title: Tên của loại nhà hàng
 * imageIcon: Ảnh đại diện cho loại nhà hàng
 * isAvailable: Nhà hàng có khả dụng không
 *
 * @created_by KhanhNQ on 07-Jan-21
 */
public class BusinessType implements Parcelable {
    private final int id;
    private final String title;
    private final @DrawableRes
    int imageIcon;
    private final boolean isAvailable;

    public BusinessType(int id, String title, int imageIcon, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.imageIcon = imageIcon;
        this.isAvailable = isAvailable;
    }

    protected BusinessType(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imageIcon = in.readInt();
        isAvailable = in.readByte() != 0;
    }

    public static final Creator<BusinessType> CREATOR = new Creator<BusinessType>() {
        @Override
        public BusinessType createFromParcel(Parcel in) {
            return new BusinessType(in);
        }

        @Override
        public BusinessType[] newArray(int size) {
            return new BusinessType[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(imageIcon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(isAvailable);
        }
    }
}
