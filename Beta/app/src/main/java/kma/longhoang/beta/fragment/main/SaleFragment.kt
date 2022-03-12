package kma.longhoang.beta.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.model.ProductModel
import org.w3c.dom.Text

class SaleFragment : Fragment() {

    private var listProduct = mutableListOf<ProductModel>()
    private var recyclerView : RecyclerView?= null
    private var tvAmout: TextView?= null
    private var btnTotal: Button?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        for (i in 0 until 50) {
            val productModel = ProductModel()
            productModel.name = ("MaSP ".plus(i))
            productModel.code = i.toString()
            productModel.price = 0.0F
            listProduct.add(productModel)
        }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView?.adapter = ProductAdapter(listProduct)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_Sale)
        btnTotal = view.findViewById(R.id.button_total)
        tvAmout = view.findViewById(R.id.text_amount)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    fun addToOrderTemp(){
        Log.d("TAG", "aaa")
//        val btnReset : ImageButton = view?.findViewById(R.id.button_reset)

    }
}