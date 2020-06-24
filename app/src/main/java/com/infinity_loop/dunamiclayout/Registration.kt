package com.infinity_loop.dunamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        title = "Account Settings"
    }
}
