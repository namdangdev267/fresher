package kma.longhoang.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var dataList = mutableListOf<Country>()
    private lateinit var tvShow : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvShow = findViewById(R.id.tv_show)
        tvShow.text = ""
        createCoroutine()
    }

    private fun createCoroutine() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val scopeGetData = CoroutineScope(Dispatchers.IO)
            withContext(scopeGetData.coroutineContext) {
                getData()
                Log.e("test",dataList.size.toString())
            }
            val scope2 = CoroutineScope(Dispatchers.Main)
            withContext(scope2.coroutineContext) {
                showData(dataList)
            }
        }
    }

    private fun showData(dataList: MutableList<Country>) {
        val stringBuilder = StringBuilder("")
        for (i in 0 until dataList.size) {
            stringBuilder.append(dataList[i].country)
                .append("/${dataList[i].countryCode}")
                .append("/\t${dataList[i].totalConfirmed}")
                .append("/\t${dataList[i].lat}")
                .append("/\t${dataList[i].lng}")
                .append("/\t${dataList[i].totalDeaths}")
                .append("/\t${dataList[i].totalRecovered}")
                .append("/\t${dataList[i].dailyConfirmed}")
                .append("/\t${dataList[i].dailyDeaths}")
                .append("/\t${dataList[i].activeCases}")
                .append("/\t${dataList[i].totalCritical}")
                .append("/\t${dataList[i].totalConfirmedPerMillionPopulation}")
                .append("/\t${dataList[i].totalDeathsPerMillionPopulation}")
                .append("/\t${dataList[i].fr}")
                .append("/\t${dataList[i].pr}")
                .append("/\t${dataList[i].lastUpdated}")
                .append("\n")
                .append("\n")
        }
        tvShow.text = stringBuilder
    }

    private fun getData(): MutableList<Country> {
        try {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.coronatracker.com/")
                .build().create(APIService::class.java)
            val retrofitData = retrofit.getData()
//        retrofitData.enqueue(object : Callback<List<Country>> {
//            override fun onResponse(
//                call: Call<List<Country>>,
//                response: Response<List<Country>>
//            ) {
//                val stringBuilder = StringBuilder("")
//                val responseBody = response.body()
//                for (data in responseBody){
//                    stringBuilder.append(data.country)
//                }
//                Log.d("TAG", "$stringBuilder")
//            }
//            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
//                Log.d("TAG", "${t?.message}")
//            }
//
//        })
            val res = retrofitData.execute()
            val body = res.body()
            Log.e("body",body.size.toString())
            for (data in body) {
                val country = Country()
                country.country = data.country
                country.countryCode = data.countryCode
                country.totalConfirmed = data.totalConfirmed
                country.lat = data.lat
                country.lng = data.lng
                country.totalDeaths = data.totalDeaths
                country.totalRecovered = data.totalRecovered
                country.dailyConfirmed = data.dailyConfirmed
                country.dailyDeaths = data.dailyDeaths
                country.activeCases = data.activeCases
                country.totalCritical =data.totalCritical
                country.totalConfirmedPerMillionPopulation = data.totalConfirmedPerMillionPopulation
                country.totalDeathsPerMillionPopulation = data.totalDeathsPerMillionPopulation
                country.fr = data.fr
                country.pr = data.pr
                country.lastUpdated = data.lastUpdated
                dataList.add(country)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataList
    }
}