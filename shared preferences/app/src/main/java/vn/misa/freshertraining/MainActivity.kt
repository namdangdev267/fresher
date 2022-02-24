package vn.misa.freshertraining

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import vn.misa.freshertraining.PreferenceHelper.clearValues
import vn.misa.freshertraining.PreferenceHelper.customPreference
import vn.misa.freshertraining.PreferenceHelper.defaultPreference
import vn.misa.freshertraining.PreferenceHelper.password
import vn.misa.freshertraining.PreferenceHelper.userId

class MainActivity : AppCompatActivity() {

    private val prefs = customPreference(this, CUSTOM_PREF_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val edtUserId = findViewById<EditText>(R.id.edtUserId)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            prefs.userId = edtUserId.text.toString().toInt()
            prefs.password = edtPassword.text.toString()
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            prefs.clearValues
        }
        findViewById<Button>(R.id.btnShow).setOnClickListener {
            edtUserId.setText(prefs.userId.toString())
            edtPassword.setText(prefs.password)
        }
        findViewById<Button>(R.id.btnShowDefault).setOnClickListener {
            val defaultPrefs = defaultPreference(this)
            edtUserId.setText(defaultPrefs.userId.toString())
            edtPassword.setText(defaultPrefs.password)
        }
    }

    companion object {
        const val CUSTOM_PREF_NAME = "User_data"
    }
}

object PreferenceHelper {

    private const val USER_ID = "USER_ID"
    private const val USER_PASSWORD = "PASSWORD"

    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value) {
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}
