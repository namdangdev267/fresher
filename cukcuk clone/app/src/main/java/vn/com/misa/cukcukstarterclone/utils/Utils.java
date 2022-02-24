package vn.com.misa.cukcukstarterclone.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * - Mục đích Class: Utility class, chứa các phương thức được sử dụng chung trong cả ứng dụng
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class Utils {
    public static void handleException(Exception e) {
        Log.e("CukCukStarter", "ERROR: " + e);
    }

    public static String round(float value) {
        BigDecimal bd = BigDecimal.valueOf(value/1000D);
        bd = bd.setScale(10, RoundingMode.CEILING);
        if (value % 1000 == 0) {
            return String.valueOf((int) value/1000);
        }
        return String.valueOf(bd.doubleValue());
    }

    public static String formatMoney(float value) {
        return new DecimalFormat("###,###,###").format(value);
    }

    public static String dateFormat(String format, Date date) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }
}
