package com.infinity_loop.dunamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class other : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        val actionbar = supportActionBar

        actionbar!!.title = "Add other Account"
    }
}