package kma.longhoang.beta.model

data class ProductModel(
    var name: String,
    var code: String,
    var price: Float,
    var style: FilterProduct.Style,
    var color: FilterProduct.Color?= null,
)