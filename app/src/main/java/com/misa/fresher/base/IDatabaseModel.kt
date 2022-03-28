package com.misa.fresher.base

import android.content.ContentValues

interface IDatabaseModel {
    fun getContentValues(): ContentValues
}