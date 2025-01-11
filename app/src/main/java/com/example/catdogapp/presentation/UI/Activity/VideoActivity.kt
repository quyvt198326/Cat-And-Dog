package com.example.catdogapp.presentation.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.catdogapp.R
import com.example.catdogapp.adapter.VideoAdapter
import com.example.catdogapp.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager and adapter
        binding.pagerVideo.orientation = ViewPager2.ORIENTATION_VERTICAL
        videoAdapter = VideoAdapter(getVideoList())
        binding.pagerVideo.adapter = videoAdapter
    }

    private fun getVideoList(): List<Int> {
        return listOf(
            R.raw.video1,
            R.raw.video2,
            R.raw.video3,
            R.raw.video4,
            R.raw.video5
        )
    }

    override fun onResume() {
        super.onResume()
        videoAdapter.resumeMediaPlayer()
    }

    override fun onPause() {
        super.onPause()
        videoAdapter.pauseMediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoAdapter.releaseMediaPlayer()
    }

}
