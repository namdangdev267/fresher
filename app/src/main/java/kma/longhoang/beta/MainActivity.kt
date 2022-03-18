package kma.longhoang.beta


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration =
            AppBarConfiguration(navController.graph, findViewById<DrawerLayout>(R.id.drawerLayout))
        appBarConfiguration.let {
            (findViewById<NavigationView>(R.id.nav_menu)).setupWithNavController(
                navController
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

    fun drawerView() {
        findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (findViewById<DrawerLayout>(R.id.drawerLayout).isDrawerOpen(GravityCompat.START)) {
            findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}