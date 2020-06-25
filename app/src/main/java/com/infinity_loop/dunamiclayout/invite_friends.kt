package com.infinity_loop.dunamiclayout

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.ktx.Firebase
import org.json.JSONException


class invite_friends : AppCompatActivity() {

    lateinit var code:TextView;
    lateinit var noOfSuccess: TextView;
    lateinit var coins:TextView;

    lateinit var invite:Button;

    lateinit var lists:ListView;

    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_friends)
        val actionbar = supportActionBar
        actionbar!!.title = "Invite Friends to Earn Coins"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        requestQueue = Volley.newRequestQueue(this)

        code = findViewById(R.id.tv_code)
        noOfSuccess = findViewById(R.id.tv_success)
        coins = findViewById(R.id.tv_coins)

        invite = findViewById(R.id.btn_invite)

        lists = findViewById(R.id.listView)

        jsonParse()

    }

    private fun createLink() {
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("https://www.example.com/")
            domainUriPrefix = "https://dunamiclayout.page.link"
            // Open links with this app on Android
            androidParameters { }
            // Open links with com.example.ios on iOS
            iosParameters("com.example.ios") { }
        }

        val dynamicLinkUri = dynamicLink.uri
        Log.d("TAG", " Long refer" + dynamicLink.uri)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun jsonParse() {
        val url = "http://192.168.43.49/RewardsProject/output.json"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            var jsonArray = response.getJSONArray("tblusereareq")
            for (i in 0 until jsonArray.length()) {
                val tblusereareq = jsonArray.getJSONObject(i)
                val payMode = tblusereareq.getString("payMode")
                val coins = tblusereareq.getString("coins")
                val created_at = tblusereareq.getString("created_at")
                val currency = tblusereareq.getString("currency")
                val value = tblusereareq.getString("value")
                val userCode = tblusereareq.getString("userCode")
                val reqID = tblusereareq.getString("reqID")
                val status = tblusereareq.getString("status")
                val issueNote = tblusereareq.getString("issueNote")

                this.coins.append(coins)
                this.code.append(value)

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }




}