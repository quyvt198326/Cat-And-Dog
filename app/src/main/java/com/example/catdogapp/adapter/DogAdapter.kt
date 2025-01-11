package com.example.catdogapp.adapter

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catdogapp.databinding.ItemCatBinding
import com.example.catdogapp.databinding.ItemDogBinding
import com.example.catdogapp.models.CatItem
import com.example.catdogapp.models.DogItem

class DogAdapter(
    private val context: Context,
    private val listDogItem: List<DogItem>,
    private val soundList: List<Int>
) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    private var currentMediaPlayer: MediaPlayer? = null
    class DogViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val catItem = listDogItem[position]
        holder.binding.imgRvDog.setImageResource(catItem.iconId)
        holder.binding.textDog.text = catItem.text

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
        return listDogItem.size
    }
}