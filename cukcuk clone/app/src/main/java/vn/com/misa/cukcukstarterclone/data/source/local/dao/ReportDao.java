package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.model.HourReport;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 01-Feb-2021.
 */
public class ReportDao implements IReportDao {

    private final SQLiteDatabase database;

    private ReportDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static ReportDao instance;

    public static ReportDao getInstance(AppDatabase appDatabase) {
        if (instance == null) {
            instance = new ReportDao(appDatabase);
        }
        return instance;
    }

    @Override
    public List<OverallReport> getOverallReport(String date) {
        String from = date.split("to")[0];
        String to = date.split("to")[1];
        String query = "SELECT SUM(c." + Cart.TOTAL_AMOUNT + ") as " + OverallReport.AMOUNT +
                ", strftime('%Y-%m-%d', o." + Order.CREATED_AT + ") as " + OverallReport.DAY +
                " FROM " + Cart.TABLE_NAME + " c," + Order.TABLE_NAME + " o" +
                " WHERE c." + Cart.ID + " = o." + Order.CART_ID +
                " and o." + Order.CREATED_AT + " >= '" + from +
                "' and o." + Order.CREATED_AT + " <= '" + to + " 23:59:59'" +
                " GROUP BY " + OverallReport.DAY +
                " ORDER BY o." + Order.CREATED_AT + " ASC";
        Cursor cursor = database.rawQuery(query, null);
        List<OverallReport> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new OverallReport(cursor));
                cursor.moveToNext();
            }
        }

        cursor.close();
        return list;
    }

    @Override
    public List<OverallReport> getOverallCashReport(String date) {
        String from = date.split("to")[0];
        String to = date.split("to")[1];
        String query = "SELECT SUM(c." + Cart.TOTAL_AMOUNT + ") as " + OverallReport.AMOUNT +
                ", strftime('%d-%m-%Y', o." + Order.CREATED_AT + ") as " + OverallReport.DAY +
                " FROM " + Cart.TABLE_NAME + " c," + Order.TABLE_NAME + " o" +
                " WHERE c." + Cart.ID + " = o." + Order.CART_ID +
                " and o." + Order.TYPE + " = 0" +
                " and o." + Order.CREATED_AT + " >= '" + from +
                "' and o." + Order.CREATED_AT + " <= '" + to + " 23:59:59'" +
                " GROUP BY " + OverallReport.DAY +
                " ORDER BY o." + Order.CREATED_AT + " ASC";
        Cursor cursor = database.rawQuery(query, null);
        List<OverallReport> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new OverallReport(cursor));
                cursor.moveToNext();
            }
        }

        cursor.close();
        return list;
    }

    @Override
    public List<HourReport> getOverallReportByHours(String date) {
        String from = date.split("to")[0];
        String to = date.split("to")[1];
        String query = "SELECT SUM(c." + Cart.TOTAL_AMOUNT + ") as " + HourReport.AMOUNT +
                ", strftime('%H', o." + Order.CREATED_AT + ") as " + HourReport.HOUR +
                " FROM " + Cart.TABLE_NAME + " c," + Order.TABLE_NAME + " o" +
                " WHERE c." + Cart.ID + " = o." + Order.CART_ID +
                " and o." + Order.CREATED_AT + " >= '" + from +
                "' and o." + Order.CREATED_AT + " <= '" + to + " 23:59:59'" +
                " GROUP BY " + HourReport.HOUR +
                " ORDER BY o." + Order.CREATED_AT + " ASC";
        Cursor cursor = database.rawQuery(query, null);
        List<HourReport> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new HourReport(cursor));
                cursor.moveToNext();
            }
        }

        cursor.close();
        return list;
    }

    @Override
    public List<DetailsReport> getDetailsReport(String date) {
        String from = date.split("to")[0];
        String to = date.split("to")[1];
        String query = "SELECT i." + MenuItem.NAME +
                ", SUM(ci." + CartItem.QUANTITY + ") as " + DetailsReport.QUANTITY +
                ", SUM(ci." + CartItem.AMOUNT + ") as " + DetailsReport.AMOUNT +
                ", SUM(ci." + CartItem.PRICE + ") as " + DetailsReport.PRICE +
                ", SUM(ci." + CartItem.PRICE + ") - SUM(ci." + CartItem.AMOUNT + ") as " + DetailsReport.DISCOUNT +
                " FROM " + Cart.TABLE_NAME + " c," + CartItem.TABLE_NAME + " ci," + MenuItem.TABLE_NAME + " i," + Order.TABLE_NAME + " o" +
                " WHERE o." + Order.CART_ID + " = c." + Cart.ID +
                " and c." + Cart.ID + " = ci." + CartItem.CART_ID +
                " and ci." + CartItem.PRODUCT_ID + " = i." + MenuItem.ID +
                " and o." + Order.CREATED_AT + " >= '" + from +
                "' and o." + Order.CREATED_AT + " <= '" + to + " 23:59:59'" +
                " GROUP BY i." + MenuItem.ID +
                " ORDER BY " + DetailsReport.AMOUNT + " DESC";
        List<DetailsReport> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new DetailsReport(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();

        return list;
    }
}
