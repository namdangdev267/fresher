package com.misa.fresher.util

import com.misa.fresher.data.entity.Customer
import org.junit.Assert.*
import org.junit.Test

class ExtensionKtTest {
    @Test
    fun checkNull_default() {
        print(default(null, "Some thing to write"))
    }

    @Test
    fun checkNull2_default() {
        val a: Customer? = null
        val b = default(a?.toString(), "Some thing to write 2")
        print(b)
    }
}