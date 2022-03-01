package com.misa.fresher.adapter

class Product(val product_name: String, val product_abb_name: String, val product_price: Int) {
    companion object {

        private var lastContactId = 0


        fun createContactsList(numProducts: Int): ArrayList<Product> {
            val contacts = ArrayList<Product>()
            for (i in 1..numProducts) {
                contacts.add(Product("Giày " + ++lastContactId, "G" + lastContactId, 120000))
            }
            return contacts
        }
    }
}