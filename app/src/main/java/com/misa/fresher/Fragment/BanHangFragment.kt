package com.misa.fresher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.misa.fresher.Adapter.ProductAdapter
import com.misa.fresher.Fragment.ReceiverFragment

class BanHangFragment : Fragment() {

    lateinit var productList: ArrayList<Product>
    var mainActivity: MainActivity? = null
    var linearQuantity: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_ban_hang, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcvProduct = view.findViewById<RecyclerView>(R.id.rcvProduct)
        linearQuantity = view.findViewById(R.id.linearQuantity)

        productList = Product.createProductList(20)
        val adapter = ProductAdapter(productList!!)
        rcvProduct.adapter = adapter
        rcvProduct.layoutManager = LinearLayoutManager(activity)

        val itemDecoration: ItemDecoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rcvProduct.addItemDecoration(itemDecoration)

        val linearQuantity = view.findViewById<LinearLayout>(R.id.linearQuantity)

        mainActivity = activity as MainActivity?

        linearQuantity.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ViewPagerActivity::class.java)
            startActivity(intent)
        })
    }

    companion object {
        fun newInstance(): BanHangFragment{
            val fragmentBanHang = BanHangFragment()
            val args = Bundle()
            fragmentBanHang.arguments = args
            return fragmentBanHang
        }

    }
}