package vn.misa.freshertraining

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnGetImage).setOnClickListener {
            searchForDog()
        }
    }

    private fun searchForDog() {
        // Sử dụng launch và Dispatchers.IO để thực hiện request đến network
        launch(Dispatchers.IO) {
            // Sử dụng try catch để bắt lỗi khi gọi đến network
            try {
                val response = Api.apiClient.getRandomDogImage()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.message?.let {
                        withContext(Dispatchers.Main) {
                            findViewById<ImageView>(R.id.ivDog).load(it)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Error Occurred: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error Occurred: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
