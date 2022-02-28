package com.misa.fresher

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.Fragment.DSachHoaDonFragment
import com.misa.fresher.Fragment.LSuDonHangFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SearchView.OnQueryTextListener {
    val Fragment_BanHang = 0
    val Fragment_SoGiaoHang = 1
    val Fragment_DSachHoaDon = 2
    val Fragment_LSuDonHang = 3
    var currentFragment = Fragment_BanHang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.hideOverflowMenu()
        val toggle = ActionBarDrawerToggle(
            this,
            drawre_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawre_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
        replaceFragment(BanHangFragment())
        navigation_view.menu.findItem(R.id.nav_BanHang).isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_BanHang) {
            if (currentFragment != Fragment_BanHang) {
                replaceFragment(BanHangFragment())
                currentFragment = Fragment_BanHang
            }
        } else if (id == R.id.nav_SoGiaoHang) {
            if (currentFragment != Fragment_SoGiaoHang) {
                replaceFragment(SoGiaoHangFragment())
                currentFragment = Fragment_SoGiaoHang
            }
        } else if (id == R.id.nav_DSachHoaDon) {
            if (currentFragment != Fragment_DSachHoaDon) {
                replaceFragment(DSachHoaDonFragment())
                currentFragment = Fragment_DSachHoaDon
            }
        } else if (id == R.id.nav_LSuDonHang) {
            if (currentFragment != Fragment_LSuDonHang) {
                replaceFragment(LSuDonHangFragment())
                currentFragment = Fragment_LSuDonHang
            }
        }
        drawre_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawre_layout.isDrawerOpen(GravityCompat.START)) {
            drawre_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.commit()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        val itemSearch: MenuItem = menu!!.findItem(R.id.action_search)
//        searchView = itemSearch.actionView as SearchView?
//        searchView?.setOnQueryTextListener(this)
//        return true
//    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}