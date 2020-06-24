package com.infinity_loop.dunamiclayout

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.ktx.Firebase


class invite_friends : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_friends)
        val actionbar = supportActionBar
        actionbar!!.title = "Invite Friends to Earn Coins"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        var create_link : TextView = findViewById(R.id.generate_link)
        create_link.setOnClickListener {
            createLink()
        }
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


}