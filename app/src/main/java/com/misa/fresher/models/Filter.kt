package com.misa.fresher.models

import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color
import com.misa.fresher.models.enums.SortBy

data class Filter(
    var category: Category?,
    var color: Color?,
    var available: Boolean,
    var sortBy: SortBy?
)