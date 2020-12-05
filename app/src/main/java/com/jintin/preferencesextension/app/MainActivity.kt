package com.jintin.preferencesextension.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jintin.preferencesextension.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.submit.setOnClickListener {
            viewModel.setPreference(binding.newValue.text.toString())
        }
        binding.nextPage.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        viewModel.preferenceLiveData.observe(this) {
            binding.display.text = "Current value is:$it"
        }
    }
}