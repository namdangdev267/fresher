package vn.com.misa.cukcukstarterclone.data.model;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DATE_DAY_FORMAT;

/**
 * @created_by KhanhNQ on 01-Feb-2021.
 */
public class OverallReport {

    public static final String AMOUNT = "amount";
    public static final String DAY = "day";

    private float amount;
    private Date day;

    public OverallReport(float amount, Date day) {
        this.amount = amount;
        this.day = day;
    }

    public OverallReport(Cursor cursor) {
        try {
            this.amount = cursor.getFloat(cursor.getColumnIndex(AMOUNT));
            this.day = new SimpleDateFormat(DATE_DAY_FORMAT, Locale.getDefault()).parse(cursor.getString(cursor.getColumnIndex(DAY)));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    public float getAmount() {
        return amount;
    }

    public Date getDay() {
        return day;
    }
}
