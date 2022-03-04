package com.misa.fresher.model

class Product(val product_name: String, val product_abb_name: String, val product_price: Int)
{
    companion object
    {
        fun createContactsList(numProducts: Int): ArrayList<Product>
        {
            var lastContactId = 0

            val contacts = ArrayList<Product>()
            for (i in 1..numProducts)
            {
                contacts.add(Product("Giày " + ++lastContactId, "G" + lastContactId, 120000))
            }
            return contacts
        }
    }
}