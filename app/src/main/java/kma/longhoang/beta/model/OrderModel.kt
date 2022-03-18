package kma.longhoang.beta.model

data class OrderModel (
    var name: String,
    var code: String,
    var color: FilterProduct.Color?= null,
    var price: Float,
    var amount: Int,
)