package vn.com.misa.cukcukstarterclone.ui.main.addorder.dto;

import android.os.Parcel;
import android.os.Parcelable;

import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * Mục đích class: Lớp biểu diễn Nhóm món ăn để hiển thị lên View
 *
 * @created_by KhanhNQ on 26-Jan-21
 */
public class MenuGroupDto implements Parcelable {
    private final String id;
    private final String title;
    private final String iconUrl;
    private boolean isSelected;
    private int itemCount;

    public MenuGroupDto(MenuGroup menuGroup, int itemCount) {
        this.id = menuGroup.getId();
        this.title = menuGroup.getTitle();
        this.iconUrl = menuGroup.getIconUrl();
        this.isSelected = false;
        this.itemCount = itemCount;
    }

    protected MenuGroupDto(Parcel in) {
        id = in.readString();
        title = in.readString();
        iconUrl = in.readString();
        isSelected = in.readByte() != 0;
        itemCount = in.readInt();
    }

    public static final Creator<MenuGroupDto> CREATOR = new Creator<MenuGroupDto>() {
        @Override
        public MenuGroupDto createFromParcel(Parcel in) {
            return new MenuGroupDto(in);
        }

        @Override
        public MenuGroupDto[] newArray(int size) {
            return new MenuGroupDto[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(iconUrl);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeInt(itemCount);
    }
}
