package com.misa.fresher.models

class Bill (val billNumber : Int, val product : MutableList<SelectedProduct>, val customer : Customer, val total : Int){
}