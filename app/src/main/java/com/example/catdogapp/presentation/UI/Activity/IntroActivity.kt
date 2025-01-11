package com.example.catdogapp.presentation.UI.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.catdogapp.databinding.ActivityIntroBinding

class IntroActivity : ComponentActivity() {
    private lateinit var binding: ActivityIntroBinding
    private var isImgCatPressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kiểm tra trạng thái đã lưu trong SharedPreferences
        val sharedPreferences = getSharedPreferences("IntroPrefs", MODE_PRIVATE)
        isImgCatPressed = sharedPreferences.getBoolean("isCatPressed", false)

        // Nếu đã chọn imgHaveCat hoặc imgHaveDog trước đó, bỏ qua IntroActivity
        if (isImgCatPressed || sharedPreferences.contains("isCatPressed")) {
            val intent = Intent(this, Intro2Activity::class.java)
            intent.putExtra("isCatPressed", isImgCatPressed)
            startActivity(intent)
            finish()
        }

        // Xử lý sự kiện khi imgHaveCat được nhấn
        binding.imgHaveCat.setOnClickListener {
            val intent = Intent(this, Intro2Activity::class.java)
            intent.putExtra("isCatPressed", true)
            startActivity(intent)

            // Lưu trạng thái isImgCatPressed vào SharedPreferences
            sharedPreferences.edit().putBoolean("isCatPressed", true).apply()
            isImgCatPressed = true
            finish()
        }

        // Xử lý sự kiện khi imgHaveDog được nhấn
        binding.imgHaveDog.setOnClickListener {
            val intent = Intent(this, Intro2Activity::class.java)
            intent.putExtra("isCatPressed", false)
            startActivity(intent)

            // Lưu trạng thái isImgDogPressed vào SharedPreferences
            sharedPreferences.edit().putBoolean("isCatPressed", false).apply()
            finish()
        }
    }

}