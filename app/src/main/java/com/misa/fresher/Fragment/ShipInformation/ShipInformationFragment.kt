package com.misa.fresher.Fragment.ShipInformation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.Fragment.ShipInformation.Package.PackageFragment
import com.misa.fresher.Fragment.ShipInformation.Receiver.ReceiverFragment
import com.misa.fresher.Fragment.ShipInformation.Ship.ShipFragment
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentShipInformationBinding

class ShipInformationFragment: Fragment() {
    private lateinit var sharedViewModel: PublicViewModel

    private val binding: FragmentShipInformationBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentShipInformationBinding
        get() = FragmentShipInformationBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    private fun initViewModel() {
        sharedViewModel = ViewModelProvider(requireActivity()).get(PublicViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configTabLayout(view)

    }

    private fun transitionFragment(view: View) {
        binding.imgBackPayment.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configTabLayout(view: View) {
        val tabLayout = binding.tabLayoutShipping
        val viewPager = binding.vpPager

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ReceiverFragment(), "Receiver")
        viewPagerAdapter.addFragment(ShipFragment(), "Ship")
        viewPagerAdapter.addFragment(PackageFragment(), "Package")

        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
            if (!tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_left)
                    }
                    2 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_right)
                    }
                    1 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_middle)
                    }
                }
            }
        }.attach()

        binding.btnSave.setOnClickListener {
            sharedViewModel.updateInforShip((viewPagerAdapter.mFragmentList[0] as ReceiverFragment).inforShip)

            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_ship_to_fragment_payment)
        }
    }

    inner class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getItemCount(): Int = mFragmentList.size

        override fun createFragment(position: Int): Fragment = mFragmentList[position]

        fun getPageTitle(position: Int): String {
            return mFragmentTitleList[position]
        }
    }

}