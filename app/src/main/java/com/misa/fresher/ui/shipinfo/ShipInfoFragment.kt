package com.misa.fresher.ui.shipinfo

import android.text.InputType
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.ui.shipinfo.adapter.ShipInfoAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.*
import com.misa.fresher.data.model.ShipInfo

class ShipInfoFragment : BaseFragment<FragmentShipInfoBinding>(FragmentShipInfoBinding::inflate) {
    override fun initUI() {
        val viewPager2 = binding.shipInformationViewPager
        val tabLayout = binding.shipInformationTabLayout

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPagerAdapter.addFragment(Receiver(), getString(R.string.receiver_fragment_name))
        viewPagerAdapter.addFragment(Shipping(), getString(R.string.shipping_fragment_name))
        viewPagerAdapter.addFragment(Package(), getString(R.string.package_fragment_name))

        viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.getFragmentTitle(position)
            if (!tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_tab_selected_round_left)
                    }
                    viewPagerAdapter.itemCount - 1 -> {
                        tab.view.background = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_tab_selected_round_right)
                    }
                    else -> {
                        tab.view.background = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_tab_selected_middle)
                    }
                }
            }
        }.attach()
    }

    override fun initListener() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        private var fragmentList: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList: ArrayList<String> = ArrayList()

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

        fun getFragmentTitle(position: Int): String = fragmentTitleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }

    class Receiver : BaseFragment<FragmentShipInfoReceiverBinding>(FragmentShipInfoReceiverBinding::inflate) {
        override fun initUI() {
            with(binding.receiverRecView) {
                adapter = ShipInfoAdapter(fakeData()) { _ , _ ->
                    Toast.makeText(context, "You click items", Toast.LENGTH_SHORT).show()
                }
                layoutManager = LinearLayoutManager(context)
            }
        }

        private fun fakeData(): ArrayList<ArrayList<ShipInfo>> {
            val dataset = ArrayList<ArrayList<ShipInfo>>()

            val receiver = ShipInfo.Basic("Receiver", true, "", "Touch to select", InputType.TYPE_NULL, R.drawable.ic_add_black)
            val tel = ShipInfo.Basic("Tel", true, "", "Touch to enter", InputType.TYPE_CLASS_PHONE)
            val address = ShipInfo.Basic("Address", true, "", "Touch to enter", InputType.TYPE_CLASS_TEXT)
            val area = ShipInfo.Basic("Area", false, "", "Touch to select", InputType.TYPE_NULL, R.drawable.ic_arrow_forward_black)
            val ward = ShipInfo.Basic("Ward, Commune", false, "", "Touch to select", InputType.TYPE_NULL, R.drawable.ic_arrow_forward_black)
            val paid = ShipInfo.Basic("Ship paid by cust.", false, "0,0", "", InputType.TYPE_NULL, R.drawable.ic_calculate_purple_dark)

            val depositMethod = ShipInfo.Basic("Deposit method", false, "Transfer", "", InputType.TYPE_NULL, R.drawable.ic_arrow_down_black)
            val deposit = ShipInfo.Basic("Deposit", false, "0,0", "", InputType.TYPE_NULL)

            val sale = ShipInfo.Basic("Sale channel", false, "", "Touch to select", InputType.TYPE_NULL, R.drawable.ic_arrow_down_black)
            val check = ShipInfo.Checkbox(true, "Collect COD")

            dataset.add(arrayListOf(receiver))
            dataset.add(arrayListOf(tel))
            dataset.add(arrayListOf(address))
            dataset.add(arrayListOf(area))
            dataset.add(arrayListOf(ward))
            dataset.add(arrayListOf(paid))
            dataset.add(arrayListOf(depositMethod, deposit))
            dataset.add(arrayListOf(sale))
            dataset.add(arrayListOf(check))

            return dataset
        }
    }

    class Shipping : BaseFragment<FragmentShipInfoShipBinding>(FragmentShipInfoShipBinding::inflate) {

        override fun initUI() {
            with(binding.shipRecView) {
                adapter = ShipInfoAdapter(fakeData()) {  _ , _ -> Toast.makeText(context, "You click items", Toast.LENGTH_SHORT).show() }
                layoutManager = LinearLayoutManager(context)
            }
        }

        private fun fakeData(): ArrayList<ArrayList<ShipInfo>> {
            val dataset = ArrayList<ArrayList<ShipInfo>>()

            val shipPartner = ShipInfo.Basic("Shipping partner", false, "", "Touch to select", InputType.TYPE_NULL, R.drawable.ic_arrow_down_black)
            val serviceType = ShipInfo.Basic("Service type", false, "", "Touch to enter", InputType.TYPE_NULL)
            val costPaid = ShipInfo.Basic("Shipping cost paid to partner", false, "0,0", "", InputType.TYPE_NULL, R.drawable.ic_calculate_purple_dark)
            val trackingNo = ShipInfo.Basic("Tracking No.", false, "", "Touch to enter", InputType.TYPE_CLASS_TEXT)
            val note = ShipInfo.Basic("Notes", false, "", "Touch to enter", InputType.TYPE_CLASS_TEXT)
            val date = ShipInfo.Basic("Shipping date", false, "03/03/2022", "", InputType.TYPE_NULL)

            val partnerType = ShipInfo.Radio(1, arrayListOf("Organization", "Personal"))

            dataset.add(arrayListOf(partnerType))
            dataset.add(arrayListOf(shipPartner))
            dataset.add(arrayListOf(serviceType))
            dataset.add(arrayListOf(costPaid))
            dataset.add(arrayListOf(trackingNo))
            dataset.add(arrayListOf(note))
            dataset.add(arrayListOf(date))

            return dataset
        }
    }

    class Package : BaseFragment<FragmentShipInfoPackageBinding>(FragmentShipInfoPackageBinding::inflate) {
        override fun initUI() {
            with(binding.packageRecView) {
                adapter = ShipInfoAdapter(fakeData()) {  _ , _ -> Toast.makeText(context, "You click items", Toast.LENGTH_SHORT).show() }
                layoutManager = LinearLayoutManager(context)
            }
        }

        private fun fakeData(): ArrayList<ArrayList<ShipInfo>> {
            val dataset = ArrayList<ArrayList<ShipInfo>>()

            val weight = ShipInfo.Basic("Weight (g)", false, "300", "", InputType.TYPE_CLASS_NUMBER)
            val size1 = ShipInfo.Basic("Size (cm)", false, "10", "", InputType.TYPE_CLASS_NUMBER)
            val size2 = ShipInfo.Basic("", false, "10", "", InputType.TYPE_CLASS_NUMBER)
            val size3 = ShipInfo.Basic("", false, "10", "", InputType.TYPE_CLASS_NUMBER)

            dataset.add(arrayListOf(weight))
            dataset.add(arrayListOf(size1, size2, size3))
            return dataset
        }
    }
}