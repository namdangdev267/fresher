package com.misa.fresher.model

import android.graphics.drawable.Drawable

data class DrawerItem(
    val title: String,
    val icon: Drawable,
    val onClickListener: (DrawerItem) -> Unit
)
