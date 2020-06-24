package com.infinity_loop.dunamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Contect_Us : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contect__us)

        val actionbar = supportActionBar

        actionbar!!.title = "Contact Us"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
