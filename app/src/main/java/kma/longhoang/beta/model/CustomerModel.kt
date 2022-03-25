package kma.longhoang.beta.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor

class CustomerModel {
    var id: Int ?= null
    var name: String = ""
    var phone: String = ""
    constructor()
    constructor(name: String, phone: String) {
        this.name = name
        this.phone = phone
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor){
        this.id = cursor.getInt(cursor.getColumnIndex("id"))
        this.name =cursor.getString(cursor.getColumnIndex("name"))
        this.phone = cursor.getString(cursor.getColumnIndex("phone"))
    }

    fun getContentValues() : ContentValues{
        val contentValues  = ContentValues()
        contentValues.put("id", id)
        contentValues.put("name", name)
        contentValues.put("phone", phone)
        return contentValues
    }

}