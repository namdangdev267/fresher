package vn.com.misa.cukcukstarterclone.ui.order.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class OrderDto implements Parcelable {
    private final String cartId;
    private final String orderId;
    private final String name;
    private final float totalAmount;
    private final float totalPrice;
    private final float discount;
    private final float customerPayment;
    private Date createdAt;

    public OrderDto(Cart cart, Order order) {
        this.cartId = cart.getId();
        this.orderId = order.getId();
        this.name = cart.getTitle();
        this.totalAmount = cart.getTotalAmount();
        this.totalPrice = cart.getTotalPrice();
        this.discount = cart.getDiscount();
        this.customerPayment = order.getCustomerPayment();
        this.createdAt = order.getCreatedAt();
    }

    protected OrderDto(Parcel in) {
        cartId = in.readString();
        orderId = in.readString();
        name = in.readString();
        totalAmount = in.readFloat();
        totalPrice = in.readFloat();
        discount = in.readFloat();
        customerPayment = in.readFloat();
    }

    public static final Creator<OrderDto> CREATOR = new Creator<OrderDto>() {
        @Override
        public OrderDto createFromParcel(Parcel in) {
            return new OrderDto(in);
        }

        @Override
        public OrderDto[] newArray(int size) {
            return new OrderDto[size];
        }
    };

    public String getCartId() {
        return cartId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public float getCustomerPayment() {
        return customerPayment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cartId);
        parcel.writeString(orderId);
        parcel.writeString(name);
        parcel.writeFloat(totalAmount);
        parcel.writeFloat(totalPrice);
        parcel.writeFloat(discount);
        parcel.writeFloat(customerPayment);
    }
}
