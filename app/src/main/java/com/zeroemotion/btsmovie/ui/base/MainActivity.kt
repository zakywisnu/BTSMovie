package com.zeroemotion.btsmovie.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeroemotion.btsmovie.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}