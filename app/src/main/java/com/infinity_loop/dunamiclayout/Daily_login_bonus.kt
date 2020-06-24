package com.infinity_loop.dunamiclayout

import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi

class Daily_login_bonus : AppCompatActivity() {

    var showedToday = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_login_bonus)

        val actionbar = supportActionBar

        actionbar!!.title = "Earn Daily Free Coins"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val Calendar : Calendar = Calendar.getInstance()
        var year = Calendar.get(java.util.Calendar.YEAR)
        var month = Calendar.get(java.util.Calendar.MONTH)
        var day = Calendar.get(java.util.Calendar.DAY_OF_MONTH)
        var todayString =  "" + year + "" + month + "" + day

        var preferences = getSharedPreferences("PREFS", 0)
        var currentDay = preferences.getBoolean(todayString, false)
        var editor: SharedPreferences.Editor

        if (!currentDay) {
            Toast.makeText(this, "Daily reward",Toast.LENGTH_SHORT).show()
            editor = preferences.edit()
            editor.putBoolean(todayString, true)
            editor.apply()
        }
        else{
            Toast.makeText(this, "Reward received today",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}