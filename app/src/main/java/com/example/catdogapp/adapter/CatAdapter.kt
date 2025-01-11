package com.example.catdogapp.adapter

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catdogapp.databinding.ItemCatBinding
import com.example.catdogapp.models.CatItem


class CatAdapter(
    private val context: Context,
    private val listCatItem: List<CatItem>,
    private val soundList: List<Int>
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    private var currentMediaPlayer: MediaPlayer? = null

    class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = listCatItem[position]

        holder.binding.imgRvCat.setImageResource(catItem.iconId)
        holder.binding.textCat.text = catItem.text
        holder.itemView.setOnClickListener {
            currentMediaPlayer?.let {
                if (it.isPlaying) {
                    it.stop()
                }
                it.release()
            }
            // Phát âm thanh khi nhấp vào phần tử
            val mediaPlayer = MediaPlayer.create(context, soundList[position])
            mediaPlayer.start()
            // Gán mediaPlayer hiện tại
            currentMediaPlayer = mediaPlayer

            // Giải phóng tài nguyên khi âm thanh kết thúc
            mediaPlayer.setOnCompletionListener {
                it.release()
                currentMediaPlayer = null
            }
        }
    }

    fun stopCurrentMediaPlayer() {
        currentMediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        currentMediaPlayer = null
    }

    override fun getItemCount(): Int {
        return listCatItem.size
    }
}