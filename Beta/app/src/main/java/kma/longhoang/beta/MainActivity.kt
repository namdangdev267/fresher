package kma.longhoang.beta


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kma.longhoang.beta.fragment.main.DeliveryListFragment
import kma.longhoang.beta.fragment.main.SaleFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val fragmentSale = 0
    private val fragmentDeliveryList = 1
    private var currentFragment = fragmentSale
    private var navMenu: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private val fragmentViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        replaceFragment(SaleFragment())
    }

    private fun initView() {
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        navMenu = findViewById<NavigationView>(R.id.nav_menu)
    }


    fun drawerView(item: Int) {
        drawerLayout?.openDrawer(GravityCompat.START)
        navMenu?.setNavigationItemSelectedListener(this)
        navMenu?.menu?.findItem(item)?.isChecked = true
    }

    // set item click navView để chuyển đổi fragment
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_sale) {
            if (currentFragment != fragmentSale) {
                replaceFragment(SaleFragment())
                currentFragment = fragmentSale
            }
        } else if (id == R.id.nav_delivery_list) {
            if (currentFragment != fragmentDeliveryList) {
                replaceFragment(DeliveryListFragment())
                currentFragment = fragmentDeliveryList
            }
        }
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    // thay thế fragment
    fun backStackReplaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.exit_to_left, R.anim.slide_in_left, R.anim.exit_to_right)
            .replace(R.id.fragment_view, fragment).addToBackStack(null).commit()
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.exit_to_right)
            .replace(R.id.fragment_view, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}