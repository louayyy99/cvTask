package com.louay.cv

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.louay.cv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val email = binding.emailField.editText?.text
        val age = binding.ageField.editText?.text
        val name = binding.nameField.editText?.text
        val android = binding.androidScore
        val ios = binding.iosScore
        val flutter = binding.flutterScore
        var androidScore = 0
        var iosScore = 0
        var flutterScore = 0
        var i = 0
        setContentView(binding.root)
        /********/
        android.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                i = progress
                androidScore = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        ios.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                i = progress
                iosScore = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        flutter.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                i = progress
                flutterScore = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        /*******/
        binding.next.setOnClickListener {

            if (name?.isEmpty()!! || email?.isEmpty()!! || age?.isEmpty()!!) {
                showToast("Please fill all the fields")
                i++
            } else if (isEmailValid(email.toString())) {
                showToast("Email is not valid")
                i++
            }

            if (i == 0) {
                val highestValue = listOf(androidScore, iosScore, flutterScore).sorted()[2]
                if (highestValue > 80) {
                    when (highestValue) {
                        androidScore -> showToast("You are qualified for Android")
                        iosScore -> showToast("You are qualified for IOS")
                        flutterScore -> showToast("You are qualified for Flutter")
                        else -> showToast("You are qualified")
                    }
                }
                if (androidScore <= 30 && iosScore <= 30 && flutterScore <= 30) {
                    showToast("You are not qualified")
                } else {
                    showToast("You are qualified")
                }
            }
        }

        binding.reset.setOnClickListener {
            name?.clear()
            age?.clear()
            email?.clear()
            binding.radioGroup.check(binding.hommeBtn.id)
            binding.androidScore.progress = 0
            binding.iosScore.progress = 0
            binding.flutterScore.progress = 0
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}