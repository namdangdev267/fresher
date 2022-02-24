package vn.com.misa.cukcukstarterclone.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DATE_FORMAT;

/**
 * - Mục đích class: Lớp biểu diễn Giỏ hàng
 * - Gồm các thuộc tính:
 * id: Unique id cho từng giỏ hàng
 * title: Tên giỏ hàng
 * totalAmount: Tổng số tiền cần thanh toán của giỏ hàng
 * totalPrice: Tổng số tiền theo giá niêm yết của sản phẩm
 * discount: Số tiền được khuyến mãi
 * tableNumber: Số bàn
 * type: Loại đơn hàng (Order tại quán hoặc mang về)
 * status: Trạng thái giỏ hàng (Đã được thanh toán chưa)
 * createdAt: Thời gian tạo giỏ hàng
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class Cart implements Parcelable {
    public static final String TABLE_NAME = "CART";

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String TOTAL_AMOUNT = "totalAmount";
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String DISCOUNT = "discount";
    public static final String TABLE_NUMBER = "tableNumber";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String CREATED_AT = "createdAt";

    private String id;
    private String title;
    private float totalAmount;
    private float totalPrice;
    private float discount;
    private int tableNumber;
    private CartType type;
    private CartStatus status;
    private Date createdAt;

    public Cart() {
        this.id = UUID.randomUUID().toString();
        this.title = "";
        this.type = CartType.ORDER;
        this.createdAt = new Date();
    }

    public Cart(String title, float totalAmount, float totalPrice, CartType type, CartStatus status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.discount = 0;
        this.tableNumber = 0;
        this.type = type;
        this.status = status;
        this.createdAt = new Date();
    }

    public Cart(String title, float totalAmount, float totalPrice, float discount, int tableNumber, CartType type, CartStatus status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.tableNumber = tableNumber;
        this.type = type;
        this.status = status;
        this.createdAt = new Date();
    }

    public Cart(Cursor cursor) {
        try {
            this.id = cursor.getString(cursor.getColumnIndex(ID));
            this.title = cursor.getString(cursor.getColumnIndex(TITLE));
            this.totalAmount = cursor.getFloat(cursor.getColumnIndex(TOTAL_AMOUNT));
            this.totalPrice = cursor.getFloat(cursor.getColumnIndex(TOTAL_PRICE));
            this.discount = cursor.getFloat(cursor.getColumnIndex(DISCOUNT));
            this.tableNumber = cursor.getInt(cursor.getColumnIndex(TABLE_NUMBER));
            switch (cursor.getInt(cursor.getColumnIndex(TYPE))) {
                case 0:
                    this.type = CartType.ORDER;
                    break;
                case 1:
                    this.type = CartType.TAKE_AWAY;
                    break;
            }
            switch (cursor.getInt(cursor.getColumnIndex(STATUS))) {
                case 0:
                    this.status = CartStatus.WAITING;
                    break;
                case 1:
                    this.status = CartStatus.PAID;
                    break;
            }
            this.createdAt = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    protected Cart(Parcel in) {
        id = in.readString();
        title = in.readString();
        totalAmount = in.readFloat();
        totalPrice = in.readFloat();
        discount = in.readFloat();
        tableNumber = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
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

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public CartType getType() {
        return type;
    }

    public void setType(CartType type) {
        this.type = type;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(TITLE, title);
        contentValues.put(TOTAL_AMOUNT, totalAmount);
        contentValues.put(TOTAL_PRICE, totalPrice);
        contentValues.put(DISCOUNT, discount);
        contentValues.put(TABLE_NUMBER, tableNumber);
        switch (type) {
            case ORDER:
                contentValues.put(TYPE, 0);
                break;
            case TAKE_AWAY:
                contentValues.put(TYPE, 1);
                break;
        }
        switch (status) {
            case WAITING:
                contentValues.put(STATUS, 0);
                break;
            case PAID:
                contentValues.put(STATUS, 1);
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        contentValues.put(CREATED_AT, sdf.format(createdAt));
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeFloat(totalAmount);
        dest.writeFloat(totalPrice);
        dest.writeFloat(discount);
        dest.writeInt(tableNumber);
    }

    public enum CartType {
        ORDER, TAKE_AWAY
    }

    public enum CartStatus {
        WAITING(0), PAID(1);

        private final int value;

        CartStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
