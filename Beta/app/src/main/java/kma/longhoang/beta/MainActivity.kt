package kma.longhoang.beta

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kma.longhoang.beta.fragment.main.DeliveryListFragment
import kma.longhoang.beta.fragment.main.SaleFragment


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private var toolbar: Toolbar? = null
    private var drawerLayout: DrawerLayout? = null
    private var btnCart: ImageButton? = null
    private val fragmentSale = 0
    private val fragmentDeliveryList = 1
    private var currentFragment = fragmentSale
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        navView()
    }

    private fun navView() {
        setSupportActionBar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.menu.findItem(R.id.nav_sale).isChecked = true
    }

    private fun initView() {
        btnCart = findViewById(R.id.button_cart)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        replaceFragment(SaleFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_sale) {
            if (currentFragment != fragmentSale) {
                replaceFragment(SaleFragment())
                currentFragment = fragmentSale
            }
        }else if(id == R.id.nav_delivery_list){
            if (currentFragment != fragmentDeliveryList) {
                replaceFragment(DeliveryListFragment())
                currentFragment = fragmentDeliveryList
            }
        }
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_view, fragment).commit()
    }
}