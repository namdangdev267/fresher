package kma.longhoang.beta

import android.content.Context
import android.os.Looper
import android.widget.Toast

fun showNote(context: Context, content: String){
    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
}