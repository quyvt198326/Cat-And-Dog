package com.example.catdogapp.presentation.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catdogapp.R
import com.example.catdogapp.databinding.FragmentDogBinding
import com.example.catdogapp.adapter.DogAdapter
import com.example.catdogapp.models.DogItem


class DogFragment : Fragment() {
    private lateinit var binding: FragmentDogBinding
    private lateinit var dogAdapter1: DogAdapter
    private lateinit var dogAdapter2: DogAdapter
    private lateinit var dogAdapter3: DogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDog1.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvDog2.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvDog3.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )

        val listdogItem1 = listOf(
            DogItem(R.drawable.img_dog1_rv1, "Come here"),
            DogItem(R.drawable.img_dog2_rv1, "Lovely"),
            DogItem(R.drawable.img_dog3_rv1, "Call"),
            DogItem(R.drawable.img_dog4_rv1, "Let's play"),
            DogItem(R.drawable.img_dog5_rv1, "Hug"),
            DogItem(R.drawable.img_dog6_rv1, "Feeding"),
        )
        val listdogItem2 = listOf(
            DogItem(R.drawable.img_dog1_rv2, "So happy"),
            DogItem(R.drawable.img_dog2_rv2, "I love you"),
            DogItem(R.drawable.img_dog3_rv2, "Know me"),
        )
        val listdogItem3 = listOf(
            DogItem(R.drawable.img_dog1_rv3, "Hungry"),
            DogItem(R.drawable.img_dog2_rv3, "Thirsty"),
            DogItem(R.drawable.img_dog3_rv3, "Hot"),
            DogItem(R.drawable.img_dog4_rv3, "Comfortable"),
            DogItem(R.drawable.img_dog5_rv3, "Scared"),
            DogItem(R.drawable.img_dog6_rv3, "Cold"),
        )

        val soundList = listOf(
            R.raw.dog_sound1, R.raw.dog_sound2, R.raw.dog_sound3,
            R.raw.dog_sound4, R.raw.dog_sound5, R.raw.dog_sound6
        )
        dogAdapter1 = DogAdapter(requireContext(), listdogItem1, soundList)
        dogAdapter2 = DogAdapter(requireContext(), listdogItem2, soundList)
        dogAdapter3 = DogAdapter(requireContext(), listdogItem3, soundList)

        binding.rvDog1.adapter = dogAdapter1
        binding.rvDog2.adapter = dogAdapter2
        binding.rvDog3.adapter = dogAdapter3
    }

    override fun onPause() {
        super.onPause()
        dogAdapter1.stopCurrentMediaPlayer()
        dogAdapter2.stopCurrentMediaPlayer()
        dogAdapter3.stopCurrentMediaPlayer()
    }

    override fun onStop() {
        super.onStop()
        dogAdapter1.stopCurrentMediaPlayer()
        dogAdapter2.stopCurrentMediaPlayer()
        dogAdapter3.stopCurrentMediaPlayer()
    }


}