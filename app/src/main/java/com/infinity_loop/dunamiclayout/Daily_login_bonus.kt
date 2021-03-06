package com.infinity_loop.dunamiclayout

import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class Daily_login_bonus : AppCompatActivity() {

    var showedToday = false
    lateinit var time_spent:Button
    private val START_TIMER:Long = 1000*60*5

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
        // TODO: 26-06-2020 make new thread for timer --->
        time_spent = findViewById<Button>(R.id.btn_time_spent)
        time_spent.text ="GET"

        time_spent.setOnClickListener(View.OnClickListener { v ->

            var t : Thread = Thread(MyCounter(START_TIMER, 1000))
            t.run()
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    inner class MyCounter : CountDownTimer , Runnable {
        constructor(millisInFuture: Long, interval: Long) : super(
            millisInFuture,
            interval
        )

        override fun run(){
            time_spent.isClickable=false
            start()
        }

        override fun onFinish() {
            time_spent.text = "COLLECT"
            time_spent.isClickable =true
        }

        override fun onTick(millisUntilFinished: Long) {
            var min = millisUntilFinished /1000 /60
            var sec = millisUntilFinished /1000 % 60
            time_spent.text = String.format("%02d:%02d", min, sec)
        }

    }
}