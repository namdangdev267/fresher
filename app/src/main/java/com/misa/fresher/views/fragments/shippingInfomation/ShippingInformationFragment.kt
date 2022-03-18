package com.misa.fresher.views.fragments.shippingInfomation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.views.fragments.shippingInfomation.Package.PackageFragment
import com.misa.fresher.views.fragments.shippingInfomation.Receiver.ReceiverFragment
import com.misa.fresher.views.fragments.shippingInfomation.Ship.ShipFragment
import com.misa.fresher.databinding.FragmentShippingInformationBinding

class ShippingInformationFragment : Fragment() {

//    lateinit var binding: FragmentShippingInformationBinding
    lateinit var sharedViewModel: SharedViewModel

    val binding:FragmentShippingInformationBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentShippingInformationBinding
        get() = FragmentShippingInformationBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    private fun initViewModel() {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configTabLayout(view)

    }

    private fun transitionFragment(view: View) {
        binding.ivShipInforBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configTabLayout(view: View) {
        val tabLayout = binding.tablayoutShippingInfomation
        val viewPager = binding.viewpagerShippingInfomation

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

        binding.btShippingInforSave.setOnClickListener {
            sharedViewModel.updateInforShip((viewPagerAdapter.mFragmentList[0] as ReceiverFragment).inforShip)

            Navigation.findNavController(view)
                .navigate(R.id.action_shippingInformationFragment_to_billDetailFragment)
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