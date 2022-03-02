package com.misa.fresher.util

import android.content.res.ColorStateList
import android.widget.CompoundButton
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.min

class ViewPagerWithTab {
    companion object {
        fun setup(
            viewPager: ViewPager2,
            arrayButton: Array<CompoundButton>,
            normalTextColor: ColorStateList,
            selectedTextColor: ColorStateList
        ) {
            val size = min(viewPager.size, arrayButton.size)

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    arrayButton.forEach {
                        it.setTextColor(normalTextColor)
                    }
                    val pos = if (position < size) position else (size - 1)
                    arrayButton[pos].isChecked = true
                    arrayButton[pos].setTextColor(selectedTextColor)
                }
            })

            arrayButton.forEachIndexed { index, compoundButton ->
                compoundButton.setOnCheckedChangeListener { _, b ->
                    if (b) {
                        viewPager.currentItem = if (index < size) index else (size - 1)
                    }
                }
            }

            viewPager.currentItem = 0
        }
    }
}