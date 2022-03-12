package kma.longhoang.beta.model

data class ProductModel(
    var name: String?= null,
    var code: String?= null,
    var unit: List<String>?= null,
    var price: Float?= null,
    var color: List<String>?= null,
    var size: List<String>?= null,
)