package com.misa.fresher.test

import android.util.Log
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.coroutines.coroutineContext

class TestCoroutine {
    fun run() {
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            printWorld()
//        }
//        log("Hello ")
//        Thread.sleep(2000)
//        log("Kotlin!")
//        job.cancel()
//        log("Kotlin2!")

//        GlobalScope.launch(Dispatchers.Default) {
//            val result = async { getContent("https://api.github.com/") }
//            Log.d("result", result.await())
//            val result2 = async { getContent("https://api.github.com/users") }
//            Log.d("result", result2.await())
//        }

        GlobalScope.launch {
            Log.d("result", "start")
            val result1 = async { wait( 2) }
            Thread.sleep(1000)
            val result2 = async { wait( 3) }
            Log.d("result", "some thing dump")
            Log.d("result", "task 2 + ${result2.await()}")
            Log.d("result", "task 1 + ${result1.await()}")
            Log.d("result", async { wait(result1.await() + result2.await()) }.await().toString())
        }
    }

    private suspend fun printWorld() {
        for (i in 0..100) {
            log("im tired")
            delay(250)
        }
    }

    private suspend fun wait(time: Int): Int {
        delay(time * 1000L)
        return time
    }

    private fun log(message: String) {
        Log.d("Test coroutine", message)
    }

    private suspend fun getContent(url: String): String {
        try {
            val u = URL(url)
            val httpURLConnection = u.openConnection() as HttpURLConnection
            val inn = BufferedInputStream(httpURLConnection.inputStream)
            val str = inn.bufferedReader().use { it.readText() }
            httpURLConnection.disconnect()

            return str
        } catch (ex: MalformedURLException) {
            ex.printStackTrace()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return ""
    }
}