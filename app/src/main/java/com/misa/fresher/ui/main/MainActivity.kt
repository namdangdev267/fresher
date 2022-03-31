package com.misa.fresher.ui.main

import android.content.Context
import android.view.LayoutInflater
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.misa.fresher.R
import com.misa.fresher.core.BaseActivity
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.databinding.HeaderNavBinding
import com.misa.fresher.ui.login.LoginActivity
import com.misa.fresher.ui.sale.SaleFragment
import com.misa.fresher.util.get

/**
 * Activity chính chứa hầu hết các view của ứng dụng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 4
 * @updated 3/9/2022: Tạo class
 * @updated 3/15/2022: Thêm biến chung [tempCustomer] để sử dụng giữa các fragment
 * @updated 3/23/2022: Chuyển từ mvc -> mvp
 * @updated 3/31/2022: Nhận thông tin cửa hàng từ màn hình đăng nhập và hiển thị nó lên drawer
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainContract.Presenter>(), MainContract.View {

    override val getInflater: (inflater: LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    override val initPresenter: (Context) -> MainContract.Presenter
        get() = { MainPresenter(this, it) }

    private var appBarConfiguration: AppBarConfiguration? = null
    private val shop by lazy { intent.extras.get(LoginActivity.ARGUMENT_SHOP, getString(R.string.sample_shop_1)) }

    var tempCustomer: Customer? = null

    override fun initUI() {
        configDrawer()
    }

    /**
     * Cài đặt navigation drawer
     *
     * @author Nguyễn Công Chính
     * @since 3/9/2022
     *
     * @version 3
     * @updated 3/9/2022: Tạo function
     * @updated 3/17/2022: Thêm header cho drawer
     * @updated 3/25/2022: Cấm mấy thằng con/cháu/chắt được mở drawer, màn nào được mở drawer cần cài argument "can_open_drawer" = true bên nav graph
     */
    private fun configDrawer() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        appBarConfiguration?.let {
            binding.nvMenu.setupWithNavController(navController)
        }
        val headerBinding = HeaderNavBinding.inflate(layoutInflater)
        headerBinding.tvName.text = getString(R.string.sample_user_name)
        headerBinding.tvShop.text = shop
        binding.nvMenu.addHeaderView(headerBinding.root)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            if(arguments?.getBoolean(ARGUMENT_CAN_OPEN_DRAWER, false) == true) {
                binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, binding.nvMenu)
            } else {
                binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.nvMenu)
            }
        }
    }

    /**
     * Đóng mở navigation drawer
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    fun toggleDrawer() {
        if (binding.root.isDrawerOpen(binding.nvMenu)) {
            binding.root.closeDrawer(binding.nvMenu)
        } else {
            binding.root.openDrawer(binding.nvMenu)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)

        return appBarConfiguration?.let { navController.navigateUp(it) } == true || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.root.isDrawerOpen(binding.nvMenu)) {
            toggleDrawer()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val ARGUMENT_CAN_OPEN_DRAWER = "can_open_drawer"
    }
}