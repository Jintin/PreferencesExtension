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

        ActivityMainBinding.inflate(layoutInflater).let { binding ->
            setContentView(binding.root)

            binding.submit.setOnClickListener {
                viewModel.setPreference(binding.newValue.text.toString())
            }
            binding.nextPage.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
            viewModel.preferenceLiveData.observe(this) {
                println("get update from liveData : $it")
                binding.display.text = getString(R.string.display_text, it)
            }
        }

    }
}