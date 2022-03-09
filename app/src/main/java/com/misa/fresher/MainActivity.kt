package com.misa.fresher

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.test.TestCoroutine
import com.misa.fresher.util.toCurrency

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


        // example thread
//        countNumber()
        // example async task
//        CountTask().execute(13)
        TestCoroutine().run()
        val a = 5.0
        a.toCurrency()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

    private fun countNumber() {
        val handler = Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                0 -> {
                    Log.e("count", "${msg.arg1}")
                    true
                }
                1 -> {
                    Log.e("Done", "")
                    true
                }
                else -> false
            }
        }

        Thread {
            for (i in 0..10) {
                handler.sendMessage(Message().also {
                    it.what = 0
                    it.arg1 = i
                })

                Thread.sleep(1000)
            }
            handler.sendMessage(Message().also {
                it.what = 1
            })
        }.start()
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

    class CountTask: AsyncTask<Int, Int, String>() {
        override fun doInBackground(vararg p0: Int?): String {
            p0[0]?.let {
                for (i in 0..it) {
                    publishProgress(i)

                    Thread.sleep(1000)
                }
            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.e("Done", "")
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.e("count", "${values[0]}")
        }
    }
}