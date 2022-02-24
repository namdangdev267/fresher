package vn.misa.freshertraining

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        (findViewById(R.id.btnStart) as? Button)?.setOnClickListener {
            ProgressAsyncTask().execute()
        }
    }

    private inner class ProgressAsyncTask : AsyncTask<Void, Int, String>() {
        /*
        doInBackground() làm việc ở background
        Ở đây ta dùng vòng lặp for từ 0 đến 100, sau 1s ta lại tăng giá trị lên một đơn vị
        Cứ từng giá trị ta lại gửi ra cho Main Thread bằng phương thức publishProgress(i)
         */
        override fun doInBackground(vararg p0: Void?): String {
            for (i in 0..100) {
                publishProgress(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            // Khi tiến trình kết thúc thì ta trả về một String "DONE" cho doInBackground(), đây cũng là đầu vào của onPostExecute()
            return "DONE"
        }

        /*
        Ta có thể update UI của ProgressBar bằng câu lệnh progressBar.setProgress(values[0])
        Tức giá trị truyền vào publishProgress là đầu vào của onProgressUpdate, ở đây là Int. Có thể đưa vào một lúc nhiều giá trị.
         */
        override fun onProgressUpdate(vararg values: Int?) {
            values[0]?.let { progressBar?.setProgress(it) }
        }

        /*
        Ta có thể đưa ra kết quả cuối cùng ở đây trên UI Thread.
         */
        override fun onPostExecute(result: String?) {
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
        }
    }
}
