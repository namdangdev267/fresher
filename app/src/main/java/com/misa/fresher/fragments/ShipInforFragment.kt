package com.misa.fresher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.adapters.ViewPager2Adapter
import com.misa.fresher.viewModel.ShipInforViewModel

/**
 * tạo class
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class ShipInforFragment : Fragment() {
    private val viewModel: ShipInforViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_infor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configUi(view)
        backEvent(view)
        saveShipInfor(view)

    }

    /**
     * Lưu giá trị receiver
     * đang test thử, chưa xử lý xong logic
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun saveShipInfor(view: View) {
        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            Toast.makeText(requireContext(), "luu thanh cong", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }

    /**
     * set sự kiện cho nút back
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun backEvent(view: View) {
        view.findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            activity?.onBackPressed()
        }
    }

    /**
     * set Ui cho tab Layout, view pager
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configUi(view: View) {
        val ViewAdapter = ViewPager2Adapter(this)
        ViewAdapter.addFragment(RecipientFragment(), "Người nhận")
        ViewAdapter.addFragment(ShipFragment(), "Giao hàng")
        ViewAdapter.addFragment(PackageFragment(), "Gói Hàng")
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPage2)
        viewPager.adapter = ViewAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = ViewAdapter.getFragmentTittle(position)
            if (tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_left
                        )
                    }
                    2 -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_right
                        )
                    }
                    else -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_middle
                        )
                    }
                }
            }
        }.attach()

    }

}