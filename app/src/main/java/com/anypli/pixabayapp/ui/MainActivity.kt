package com.anypli.pixabayapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anypli.pixabayapp.R
import com.anypli.pixabayapp.base.BaseActivity
import com.anypli.pixabayapp.databinding.ActivityMainBinding
import com.anypli.pixabayapp.ui.images.ImagesActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity :BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvMain.setOnClickListener {
            intent = Intent(applicationContext, ImagesActivity::class.java)
            startActivity(intent)
        }
    }
}