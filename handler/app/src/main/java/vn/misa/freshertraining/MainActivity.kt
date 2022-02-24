package vn.misa.freshertraining

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var isCounting = false

    private var tvNumber: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        listenHandler()
    }

    private fun initViews() {
        tvNumber = findViewById(R.id.tvNumber)
        (findViewById(R.id.btnCount) as? Button)?.setOnClickListener {
            if (!isCounting) countNumbers()
        }
    }

    /*
    Khởi tạo ra một đối tượng Handler từ Main Thread
    Và nó sẽ lắng nghe các kết quả trả về từ Worker Thread, cụ thể hơn là các message.
    Một Handler được tạo ra thì nó cần một Looper, truyền Looper.getMainLooper() là lấy ra Looper của Main Thread
     */
    private fun listenHandler() {
        handler = Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                MSG_UPDATE_NUMBER -> {
                    isCounting = true
                    tvNumber?.text = msg.arg1.toString()
                    true
                }
                MSG_UPDATE_NUMBER_DONE -> {
                    isCounting = false
                    tvNumber?.text = "Done"
                    true
                }
                else -> false
            }
        }
    }

    /*
    Ấn vào button Start -> countNumbers() sẽ được gọi
    Công việc trong phương thức này sẽ thực hiện trên Worker Thread
    Ta thực hiện vòng for đếm từ 0 đến 10, với mỗi giá trị i ta tạo ra một đối tương Message
    Ta cần gửi giá trị i về Main Thread nên ta có thể gán i cho arg1, what là một mã thông báo cho phép nơi nhận xác định thông điệp này là gì
    Ta sẽ kết quả trả về ở listenHandler() dựa vào message.what
     */
    private fun countNumbers() {
        Thread {
            for (i in 0..10) {
                handler?.sendMessage(Message().also {
                    it.what = MSG_UPDATE_NUMBER
                    it.arg1 = i
                })
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                handler?.sendEmptyMessage(MSG_UPDATE_NUMBER_DONE)
            }
        }.start()
    }

    companion object {
        const val MSG_UPDATE_NUMBER = 100
        const val MSG_UPDATE_NUMBER_DONE = 101
    }
}
