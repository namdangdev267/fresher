package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLayer.text = "LayerList";

        binding.btnRotate.setOnClickListener {
            val rotate = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_rotate)
            it.startAnimation(rotate)
        }

        binding.btnSlide.setOnClickListener {
            val rotate = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_slide)
            it.startAnimation(rotate)
        }

        binding.etSearch.doAfterTextChanged {
            Toast.makeText(this, it?.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}