package com.infinity_loop.dunamiclayout

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ebanx.swipebtn.SwipeButton
import com.infinity_loop.dunamiclayout.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img = BitmapFactory.decodeResource(resources, R.drawable.nobody)
        val round = RoundedBitmapDrawableFactory.create(resources, img)
        round.isCircular = true
        imageView.setImageDrawable(round)
        var btn : SwipeButton = findViewById(R.id.logout)
        var contect : TextView = findViewById(R.id.contact_us)
        var Invite : TextView = findViewById(R.id.invite)
        var daily : TextView = findViewById(R.id.daily_bonus)
        var setting : TextView = findViewById(R.id.Settings)
        var reedem :TextView = findViewById(R.id.reedem)
        var payoutSummery:TextView = findViewById(R.id.payment)

        payoutSummery.setOnClickListener {
            val intent = Intent(this, PayoutSummery::class.java)
            startActivity(intent)
        }
        reedem.setOnClickListener {
            val intent = Intent(this, redeem::class.java)
            startActivity(intent)
        }
        btn.setOnStateChangeListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        contect.setOnClickListener {
            val intent = Intent(this, Contect_Us::class.java)
            startActivity(intent)
        }
        Invite.setOnClickListener {
            val intent = Intent(this, invite_friends::class.java)
            startActivity(intent)
        }
        daily.setOnClickListener {
            val intent = Intent(this, Daily_login_bonus::class.java)
            startActivity(intent)
        }
        setting.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
//    private fun jsonParse() {
//        val url = "http://192.168.43.49/RewardsProject/userea.json"
//        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
//                response ->try {
//            var jsonArray = response.getJSONArray("userea")
//            for (i in 0 until jsonArray.length()) {
//                val tblusereareq = jsonArray.getJSONObject(i)
//                val coins = tblusereareq.getString("coins")
//                val user_id = tblusereareq.getString("user_id")
//                if (user_id == "17561") {
//                    animationView.append("$coins")
//                }
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        }, Response.ErrorListener { error -> error.printStackTrace() })
//        RequestQueue.add(request)
//    }
}
