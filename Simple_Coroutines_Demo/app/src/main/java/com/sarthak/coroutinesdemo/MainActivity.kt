package com.sarthak.coroutinesdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT1 = "Result#1"
    private val RESULT2 = "Result#2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeAPIRequest()
            }
        }
    }

    private fun setText(input: String) {
        val newText = textView.text.toString() + "\n$input"
        textView.text = newText
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Main) {
            setText(input)
        }
    }

    private suspend fun fakeAPIRequest() {
        val result1 = getResult1FromApi()
        println("debug: $result1")
        setTextOnMainThread(result1)
        val result2 = getResult2FromApi()
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        delay(100)
        return RESULT1
    }

    private suspend fun getResult2FromApi(): String {
        logThread("getResult2FromApi")
        delay(50)
        return RESULT2
    }

    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}
