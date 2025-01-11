package com.example.catdogapp.presentation.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catdogapp.R
import com.example.catdogapp.adapter.CatAdapter
import com.example.catdogapp.databinding.FragmentCatBinding
import com.example.catdogapp.models.CatItem


class CatFragment : Fragment() {
    private lateinit var binding: FragmentCatBinding
    private lateinit var catAdapter1: CatAdapter
    private lateinit var catAdapter2: CatAdapter
    private lateinit var catAdapter3: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCat1.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvCat2.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvCat3.layoutManager = GridLayoutManager(
            context, 3,
            LinearLayoutManager.VERTICAL, false
        )

        val listCatItem1 = listOf(
            CatItem(R.drawable.img_cat1_rv1, "Come here"),
            CatItem(R.drawable.img_cat2_rv1, "Lovely"),
            CatItem(R.drawable.img_cat3_rv1, "Call"),
            CatItem(R.drawable.img_cat4_rv1, "Let's play"),
            CatItem(R.drawable.img_cat5_rv1, "Hug"),
            CatItem(R.drawable.img_cat6_rv1, "Feeding"),
        )
        val listCatItem2 = listOf(
            CatItem(R.drawable.img_cat1_rv2, "So happy"),
            CatItem(R.drawable.img_cat2_rv2, "I love you"),
            CatItem(R.drawable.img_cat3_rv2, "Know me"),
        )
        val listCatItem3 = listOf(
            CatItem(R.drawable.img_cat1_rv3, "Hungry"),
            CatItem(R.drawable.img_cat2_rv3, "Thirsty"),
            CatItem(R.drawable.img_cat3_rv3, "Hot"),
            CatItem(R.drawable.img_cat4_rv3, "Comfortable"),
            CatItem(R.drawable.img_cat5_rv3, "Scared"),
            CatItem(R.drawable.img_cat6_rv3, "Cold"),
        )
        val soundList = listOf(
            R.raw.voice_cat1,
            R.raw.voice_cat2,
            R.raw.voice_cat3,
            R.raw.voice_cat4,
            R.raw.voice_cat5,
            R.raw.voice_cat6
        )
        catAdapter1 = CatAdapter(requireContext(), listCatItem1, soundList)
        catAdapter2 = CatAdapter(requireContext(), listCatItem2, soundList)
        catAdapter3 = CatAdapter(requireContext(), listCatItem3, soundList)

        binding.rvCat1.adapter = catAdapter1
        binding.rvCat2.adapter = catAdapter2
        binding.rvCat3.adapter = catAdapter3
    }

    override fun onPause() {
        super.onPause()
        catAdapter1.stopCurrentMediaPlayer()
        catAdapter2.stopCurrentMediaPlayer()
        catAdapter3.stopCurrentMediaPlayer()
    }

    override fun onStop() {
        super.onStop()
        catAdapter1.stopCurrentMediaPlayer()
        catAdapter2.stopCurrentMediaPlayer()
        catAdapter3.stopCurrentMediaPlayer()
    }
}