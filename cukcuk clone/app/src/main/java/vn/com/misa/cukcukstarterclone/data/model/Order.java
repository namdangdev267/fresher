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
 * - Mục đích class: Lớp biểu diễn Hóa đơn
 * - Gồm các thuộc tính
 * id: Unique id cho từng hóa đơn
 * type: Kiểu thanh toán (Tiền mặt hoặc thẻ ngân hàng)
 * customerPayment: Số tiền khách hàng thanh toán
 * cartId: id tham chiếu tới giỏ hàng của hóa đơn
 * createdAt: Thời gian tạo hóa đơn
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class Order implements Parcelable {
    public static final String TABLE_NAME = "PRODUCT_ORDER";

    public static final String ID = "id";
    public static final String TYPE = "paymentType";
    public static final String CUSTOMER_PAYMENT = "customerPayment";
    public static final String CART_ID = "cardId";
    public static final String CREATED_AT = "createdAt";

    private String id;
    private PaymentType paymentType;
    private float customerPayment;
    private String cartId;
    private Date createdAt;

    public Order(PaymentType paymentType, float customerPayment, String cartId) {
        this.id = UUID.randomUUID().toString();
        this.paymentType = paymentType;
        this.customerPayment = customerPayment;
        this.cartId = cartId;
        this.createdAt = new Date();
    }

    public Order(Cursor cursor) {
        try {
            this.id = cursor.getString(cursor.getColumnIndex(ID));
            switch (cursor.getInt(cursor.getColumnIndex(TYPE))) {
                case 0:
                    this.paymentType = PaymentType.CASH;
                    break;
                case 1:
                    this.paymentType = PaymentType.CARD;
                    break;
            }
            this.customerPayment = cursor.getFloat(cursor.getColumnIndex(CUSTOMER_PAYMENT));
            this.cartId = cursor.getString(cursor.getColumnIndex(CART_ID));
            this.createdAt = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(cursor.getString(cursor.getColumnIndex(CREATED_AT)));

        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    protected Order(Parcel in) {
        id = in.readString();
        customerPayment = in.readFloat();
        cartId = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public float getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(float customerPayment) {
        this.customerPayment = customerPayment;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        switch (paymentType) {
            case CASH:
                contentValues.put(TYPE, 0);
                break;
            case CARD:
                contentValues.put(TYPE, 1);
                break;
        }
        contentValues.put(CUSTOMER_PAYMENT, customerPayment);
        contentValues.put(CART_ID, cartId);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        contentValues.put(CREATED_AT, sdf.format(createdAt));
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeFloat(customerPayment);
        parcel.writeString(cartId);
    }

    public enum PaymentType {
        CASH(0), CARD(1);

        private final int value;

        PaymentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
