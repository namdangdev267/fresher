package kma.longhoang.beta

import android.content.Context
import android.widget.Toast

class ShowNote {
    fun toast(context: Context, content: String){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }
}