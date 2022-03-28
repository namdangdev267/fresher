package com.misa.fresher.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * - Class's purpose: Class helper for share preference
 *
 * @author HTLong
 * @edit_at
 */

object SharePreferenceHelper {
    const val KEY_LOGIN: String = "KEY_LOGIN"
    private const val PREF_FILE: String = "APP_PREF"

    fun setString(context: Context, key: String, value: String) {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setInt(context: Context, key: String, value: Int) {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun setBoolean(context: Context, key: String, value: Boolean) {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String, defValue: String): String {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getString(key, null) ?: defValue
    }

    fun getInt(context: Context, key: String, defValue: Int): Int {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getInt(key, defValue)
    }

    fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {
        val settings: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return settings.getBoolean(key, defValue)
    }
}