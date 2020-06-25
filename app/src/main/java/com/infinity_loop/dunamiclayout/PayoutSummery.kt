package com.infinity_loop.dunamiclayout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
class PayoutSummery : AppCompatActivity() {
    private lateinit var textView: TextView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payout_summery)
        val actionbar = supportActionBar
        actionbar!!.title = "Payout Summery"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        textView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.parse)
        requestQueue = Volley.newRequestQueue(this)
        button.setOnClickListener {
            jsonParse()
        }
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
                if (status == "P") {
                    textView.append("$userCode $reqID $currency $value $coins $created_at $payMode $status $issueNote \n\n")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}