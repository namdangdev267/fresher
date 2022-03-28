package com.misa.fresher.data.model.ship

import com.misa.fresher.utils.Enums

sealed class ShipInfo(val type: Enums.ShipInfo) {
    data class Basic(
        var title: String,
        var isRequired: Boolean = false,
        var content: String = "",
        var hint: String = "",
        var inputType: Int = 0,
        var endIcon: Int = 0
    ) : ShipInfo(Enums.ShipInfo.BASIC)

    data class Checkbox(
        var isChecked: Boolean = true,
        var content: String = ""
    ) : ShipInfo(Enums.ShipInfo.CHECKBOX)

    data class Radio(
        var checked: Int = 0,
        var contents: ArrayList<String> = arrayListOf("", ""),
    ) : ShipInfo(Enums.ShipInfo.RADIO)
}

