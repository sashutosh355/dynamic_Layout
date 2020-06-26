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
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class invite_friends : AppCompatActivity() {

    lateinit var code:TextView;
    lateinit var noOfSuccess: TextView;
    lateinit var coins:TextView;

    lateinit var invite:Button;

    internal lateinit var lists:ListView;
    var dataList = ArrayList<HashMap<String, String>>()

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

        lists = findViewById(R.id.listview1)

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
        val url = "http://192.168.43.49/RewardsProject/package.json"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            var jsonArray = response.getJSONArray("inviteArr")
            for (i in 0 until jsonArray.length()) {
                val inviteArr = jsonArray.getJSONObject(i)

                val map = HashMap<String, String>()
                map["fno"] = inviteArr.getString("fno")
                map["cns"] = inviteArr.getString("cns")
                dataList.add(map)
            }
            lists.adapter = CustomAdapter(this@invite_friends, dataList)
        }
        catch (e: JSONException) {

            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}

class CustomAdapter(private val context: Context,
                    private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataList = dataList[position]

        val rowView = inflater.inflate(R.layout.listview_invite_friends, parent, false)
        rowView.findViewById<TextView>(R.id.friends).text = dataList["fno"]
        rowView.findViewById<TextView>(R.id.coins_granted).text = dataList["cns"]

//        Picasso.get()
//            .load(dataitem.get("image"))
//            .resize(50, 50)
//            .centerCrop()
//            .into(rowView.findViewById<ImageView>(R.id.row_image))

        rowView.tag = position
        return rowView
    }
}