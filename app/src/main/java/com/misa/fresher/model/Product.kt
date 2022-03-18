package com.misa.fresher.model

class Product(
    val productId: Int,
    val productName: String,
    val productSKU: String,
    val productPrice: Int,
    val color: String,
    val size: String
)
{
    companion object{
        fun fakedat():MutableList<Product>
        {
            val contact= mutableListOf<Product>()
            contact.add(Product(1, "T-Shirt", "G1", 120000,  "red", "L"))
            contact.add(Product(2, "Shoe", "G2", 60000,  "blue", "XL"))
            contact.add(Product(3, "Skin", "G3", 120000,  "while", "L"))
            contact.add(Product(4, "Sneaker", "G4", 80000,  "orange", "M"))
            contact.add(Product(5, "Q", "G5", 45000,  "green", "big"))
            contact.add(Product(6, "R", "G6", 120000, "black", "small"))
            contact.add(Product(7, "C", "G7", 120000,  "blue", "XXL"))
            return contact
        }
    }
}