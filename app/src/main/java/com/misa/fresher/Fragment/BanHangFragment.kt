package com.misa.fresher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.misa.fresher.Adapter.ProductAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BanHangFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var rcvProduct: RecyclerView
    lateinit var productList: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_ban_hang, container, false)

        rcvProduct = view.findViewById(R.id.rcvProduct)
        productList = Product.createProductList(20)
        val adapter = ProductAdapter(productList)
        rcvProduct.adapter = adapter
        rcvProduct.layoutManager = LinearLayoutManager(activity)

        val itemDecoration: ItemDecoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rcvProduct.addItemDecoration(itemDecoration)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BanHangFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}