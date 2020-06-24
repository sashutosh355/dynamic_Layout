package com.infinity_loop.dunamiclayout

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.infinity_loop.dunamiclayout.ui.login.LoginActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class redeem : AppCompatActivity() {
    internal var helper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redeem)
        val actionbar = supportActionBar
        actionbar!!.title = "Choose Payment Method"
        var listofData: ArrayList<UserInfo> = helper.listOfUserInfo()
        for(userInfo in listofData){
            Log.e("Name===> ", userInfo.name)
        }

        var listView:ListView = findViewById(R.id.listView)
        var listAdapter = UserListAdapter(this, listofData)
        listView.adapter = listAdapter


        listView.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(this, AddActivity::class.java)
            intent.putExtra("ADD", false)
            intent.putExtra("ID",listofData[position].id)
            startActivity(intent)
        }
        var button: Button = findViewById(R.id.history)
        var AdapterView: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, ReedemHistory::class.java)
            startActivity(intent)
        }
        AdapterView.setOnClickListener {
            val colors = arrayOf("payTm", "Other")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose Action").setItems(colors, DialogInterface.OnClickListener { dialog, which ->
                // The 'which' argument contains the index position of the selected item
                if (which == 0) {
                    val intent = Intent(this, AddActivity::class.java)
                    if (intent != null) {
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@redeem, " Error, Please Try Again...", Toast.LENGTH_SHORT).show()
                    }
                }
                if (which == 1) {
                val intent = Intent(this, other::class.java)
                if (intent != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this@redeem, " Error, Please Try Again...", Toast.LENGTH_SHORT).show()
                }
            }

            })
            builder.show()

    }
    }
}
class UserListAdapter(context: Context, arrayOfData: ArrayList<UserInfo>)  : BaseAdapter() {

    var arrayOfData : ArrayList<UserInfo>;
    private val mInflator: LayoutInflater

    init {
        this.arrayOfData = arrayOfData
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfData[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfData.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.listview_row, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.nameTV.text = arrayOfData[position].name
        vh.phone.text = ""+arrayOfData[position].phone
        return view
    }
    private class ListRowHolder(row: View?) {
        public val nameTV: TextView
        public val phone : TextView

        init {
            this.nameTV = row?.findViewById(R.id.name) as TextView
            this.phone = row?.findViewById(R.id.number) as TextView
        }
    }
}

