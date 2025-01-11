package com.example.catdogapp.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catdogapp.presentation.UI.Fragment.TranslateFragment
import com.example.catdogapp.databinding.ItemVideoBinding
import com.example.catdogapp.presentation.UI.Activity.VideoActivity

class VideoAdapter(private val videoList: List<Int>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    var currentMediaPlayer = TranslateFragment.currentMediaPlayer
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoId = videoList[position]
        val context = holder.binding.root.context
        val videoUri = Uri.parse("android.resource://${context.packageName}/$videoId")
        holder.binding.videoView.setVideoURI(videoUri)
        holder.binding.videoView.setOnPreparedListener { mp ->
            currentMediaPlayer = mp
            currentMediaPlayer?.isLooping = true
            currentMediaPlayer?.start()
            Log.d("MediaPlayer", "Video started playing")
            holder.binding.imagePlayVideo.visibility = View.GONE

            holder.binding.layoutVideo.setOnClickListener {
                if(currentMediaPlayer?.isPlaying == true){
                    holder.binding.imagePlayVideo.visibility = View.VISIBLE
                    currentMediaPlayer!!.pause()
                }else{
                    holder.binding.imagePlayVideo.visibility = View.GONE
                    currentMediaPlayer!!.start()
                }
            }
        }


        holder.binding.imageBackVideo.setOnClickListener {
            releaseMediaPlayer()
            (context as VideoActivity).finish()
        }
    }



    override fun getItemCount(): Int {
        return videoList.size
    }

    class VideoViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)

    fun releaseMediaPlayer() {
        currentMediaPlayer?.let {
            it.stop()
            it.release()
            currentMediaPlayer = null
        }
    }

    fun stopMediaPlayer() {
        currentMediaPlayer?.let {
            it.stop()
        }
    }

    fun pauseMediaPlayer() {
        currentMediaPlayer?.let {
            it.pause()
        }
    }

    fun resumeMediaPlayer() {
        currentMediaPlayer?.let {
            it.start()
        }
    }
}