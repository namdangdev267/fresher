package com.misa.fresher.data.model

import android.content.ContentValues

interface IDatabaseModel {
    fun getContentValues(): ContentValues
}