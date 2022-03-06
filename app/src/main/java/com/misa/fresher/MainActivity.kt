package com.misa.fresher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.model.DrawerItem
import com.misa.fresher.ui.adapter.DrawerAdapter
import com.misa.fresher.util.getColorById

class MainActivity : AppCompatActivity() {

//    private var _binding: ActivityMainBinding? = null
    // Chỉ sử dụng ở giữa onCreate và onDestroy
//    private val binding get() = _binding!!
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val navController by lazy { binding.navHostFragment.findNavController() }

    private var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.rcvDrawer.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
//        binding.rcvDrawer.adapter = DrawerAdapter(fakeData())
//        binding.rcvDrawer.addItemDecoration(
//            DividerItemDecoration(
//                baseContext,
//                DividerItemDecoration.VERTICAL
//            )
//        )
//        binding.navView.

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.dlMain)

        appBarConfiguration?.let {
//            binding.toolbar.setupWithNavController(navController, it)
            binding.navView.setupWithNavController(navController)
        }

//        binding.navView.itemTextColor = resources.getColorById(R.color.red)
//        binding.navView.
        binding.navView.setItemTextAppearance(R.style.Theme_FreshersApplication_Nav_TextItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

    fun openDrawer() {
        binding.root.openDrawer(GravityCompat.START)
    }

//    private fun fakeData(): Array<DrawerItem> = arrayOf(
//        DrawerItem(resources.getString(R.string.sale), resources.getDrawable(R.drawable.ic_cart)) {
//
//        },
//        DrawerItem(resources.getString(R.string.shipping), resources.getDrawable(R.drawable.ic_shipping)) {}
//        DrawerItem(resources.getString(R.string.sale), resources.getDrawable(R.drawable.ic_cart)) {
//            navigate(R.id.sale_fragment)
//        },
//        DrawerItem(resources.getString(R.string.shipping), resources.getDrawable(R.drawable.ic_shipping)) {
//            navigate(R.id.shipping_fragment)
//        },
//    )

//    private fun navigate(id: Int) {
//        val navOption =
//            navController.currentDestination?.id?.let {
//                NavOptions.Builder().setPopUpTo(it, false).build()
//            }
//        navController.navigate(id, null, navOption)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}