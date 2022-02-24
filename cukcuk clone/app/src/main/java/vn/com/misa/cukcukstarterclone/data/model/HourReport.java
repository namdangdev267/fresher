package vn.com.misa.cukcukstarterclone.data.model;

import android.database.Cursor;

import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 04-Feb-2021.
 */
public class HourReport {
    public static final String AMOUNT = "amount";
    public static final String HOUR = "hour";

    private float amount;
    private int hour;

    public HourReport(Cursor cursor) {
        try {
            this.amount = cursor.getFloat(cursor.getColumnIndex(AMOUNT));
            this.hour = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HOUR)));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    public float getAmount() {
        return amount;
    }

    public int getHour() {
        return hour;
    }
}
