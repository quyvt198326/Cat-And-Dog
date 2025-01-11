package com.example.catdogapp.presentation.UI.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catdogapp.databinding.ActivityIntro2Binding

class Intro2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIntro2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntro2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val isCatPressed = intent.getBooleanExtra("isCatPressed", false)
        binding.imgSelection1.setOnClickListener {
            putIntent1(isCatPressed)
        }
        binding.imgSelection2.setOnClickListener {
            putIntent2(isCatPressed)
        }
        binding.imgSelection3.setOnClickListener {
            putIntent1(isCatPressed)
        }
    }

    private fun putIntent2(isCatPressed: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("isCatPressed", isCatPressed)
        intent.putExtra("selection", "translation")
        startActivity(intent)
    }

    private fun putIntent1(isCatPressed: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("isCatPressed", isCatPressed)
        intent.putExtra("selection", "sound")
        startActivity(intent)

    }
}