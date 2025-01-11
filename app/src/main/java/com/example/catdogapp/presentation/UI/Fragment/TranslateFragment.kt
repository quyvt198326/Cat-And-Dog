package com.example.catdogapp.presentation.UI.Fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.catdogapp.R
import com.example.catdogapp.databinding.FragmentTranslateBinding
import com.example.catdogapp.presentation.UI.Activity.VideoActivity
import com.example.catdogapp.util.TextTranslateUtils
import java.util.Locale


class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding

    private var RQ_SPEECH_REC = 102
    private var isCatPressed = false
    private var isDogPressed = true
    private var useRandomText = false

    companion object {
        var currentMediaPlayer: MediaPlayer? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTranslateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCatPressed = requireActivity().intent.getBooleanExtra("isCatPressed", false)
        updateButtonState()
        binding.btnCat.setOnClickListener {
            isCatPressed = true
            isDogPressed = false
            updateButtonState()
        }
        binding.btnDog.setOnClickListener {
            isCatPressed = false
            isDogPressed = true
            updateButtonState()
        }
        binding.imageHumanTranslate.setOnClickListener {
            stopCurrentMediaPlayer()
            askSpeechInput(useRandomText = false)
        }
        binding.imageDogCatTranslate.setOnClickListener {
            stopCurrentMediaPlayer()
            askSpeechInput(useRandomText = true)
        }
        binding.imgVideo.setOnClickListener {
            stopCurrentMediaPlayer()
            val intent = Intent(requireContext(), VideoActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_SPEECH_REC && resultCode == AppCompatActivity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (useRandomText) {
                setRandomTextRecord()
            } else {
                setTextRecord(result)
            }
        }
    }

    private fun askSpeechInput(useRandomText: Boolean) {
        this.useRandomText = useRandomText
        if (!SpeechRecognizer.isRecognitionAvailable(requireContext())) {
            Toast.makeText(
                requireContext(),
                "Speech recognition is not available",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }

    private fun setTextRecord(result: ArrayList<String>?) {
        binding.textTranslate.text = result?.get(0)
        if (isCatPressed) {
            val mediaPlayer = MediaPlayer.create(context, R.raw.voice_cat1)
            setupMediaPlayer(mediaPlayer)
        } else {
            val mediaPlayer = MediaPlayer.create(context, R.raw.dog_sound1)
            setupMediaPlayer(mediaPlayer)
        }
    }

    private fun setRandomTextRecord() {
        val randomText = TextTranslateUtils.listOfText.random()
        binding.textTranslate.text = randomText
        if (isCatPressed) {
            val mediaPlayer = MediaPlayer.create(context, R.raw.voice_cat1)
            setupMediaPlayer(mediaPlayer)
        } else {
            val mediaPlayer = MediaPlayer.create(context, R.raw.dog_sound1)
            setupMediaPlayer(mediaPlayer)
        }
    }

    private fun updateButtonState() {
        if (isCatPressed) {
            binding.btnCat.setBackgroundResource(R.drawable.button_selected)
            binding.btnDog.setBackgroundResource(R.drawable.button_none_selected_dog)
            binding.imageDogCatTranslate.setImageResource(R.drawable.img_cat_record_voice)
            binding.translateLayout.setBackgroundResource(R.color.cat_background_theme)
        } else {
            binding.btnCat.setBackgroundResource(R.drawable.button_none_selected_cat)
            binding.btnDog.setBackgroundResource(R.drawable.button_selected)
            binding.imageDogCatTranslate.setImageResource(R.drawable.img_dog_record_voice)
            binding.translateLayout.setBackgroundResource(R.color.dog_background_theme)
        }
    }


    private fun setupMediaPlayer(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()

        currentMediaPlayer = mediaPlayer

        mediaPlayer.setOnCompletionListener {
            it.release()
            currentMediaPlayer = null
        }
    }


    override fun onStop() {
        super.onStop()
        stopCurrentMediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCurrentMediaPlayer()
    }

    fun stopCurrentMediaPlayer() {
        currentMediaPlayer?.release()
    }



}