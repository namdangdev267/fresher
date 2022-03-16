package com.misa.fresher.model

class Product(
    val productId:Int,
    val productName: String,
    val productSKU: String,
    val productPrice: Int,
    val quantity:Int)
{
    companion object
    {
        fun createContactsList(numProducts: Int): ArrayList<Product>
        {
            var lastContactId = 0

            val contacts = ArrayList<Product>()
            for (i in 1..numProducts)
            {
                contacts.add(Product(i,"Giày " + ++lastContactId, "G" + lastContactId, 120000,5))
            }
            return contacts
        }
        fun fakeData():ArrayList<Product>
        {
            val contacts = ArrayList<Product>()
            contacts.add(Product(1,"Áo sơ mi" , "G", 120000,5))
            contacts.add(Product(2,"Giày Nike", "G", 60000,0))
            contacts.add(Product(3,"Giày Addidas", "G" , 120000,5))
            contacts.add(Product(4,"Bộ áo Bolo", "G", 80000,0))
            contacts.add(Product(5,"Quần Âu", "G", 45000,5))
            contacts.add(Product(6,"Áo trắng", "G", 120000,0))
            contacts.add(Product(7,"Thuốc", "G", 120000,5))
            return contacts
        }
    }
}