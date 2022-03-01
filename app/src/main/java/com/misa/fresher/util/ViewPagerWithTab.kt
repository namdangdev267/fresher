package com.misa.fresher.util

import android.content.res.ColorStateList
import android.widget.CompoundButton
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2

class ViewPagerWithTab {
    companion object {
        fun setup(
            viewPager: ViewPager2,
            arrayButton: Array<CompoundButton>,
            normalTextColor: ColorStateList,
            selectedTextColor: ColorStateList
        ) {
            val size = if (viewPager.size >= arrayButton.size) {
                viewPager.size
            } else {
                arrayButton.size
            }

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    arrayButton.map {
                        it.setTextColor(normalTextColor)
                    }
                    if (position < size) {
                        arrayButton[position].isChecked = true
                        arrayButton[position].setTextColor(selectedTextColor)
                    } else {
                        arrayButton[size - 1].isChecked = true
                        arrayButton[size - 1].setTextColor(selectedTextColor)
                    }
                }
            })

            for (i in arrayButton.indices) {
                arrayButton[i].setOnCheckedChangeListener { _, b ->
                    if (b) {
                        if (i < size) {
                            viewPager.currentItem = i
                        } else {
                            viewPager.currentItem = size - 1
                        }
                    }
                }
            }

            viewPager.currentItem = 0
        }
    }
}