package com.infinity_loop.dunamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contect__us.*

class Contect_Us : AppCompatActivity() {
    internal var helper = Contect_us_DATABASE_HELPER(this)
    var isAdd: Boolean = false;
    var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contect__us)
        val actionbar = supportActionBar
        actionbar!!.title = "Contect_us"
        var name : EditText = findViewById(R.id.editText)
        var number :EditText = findViewById(R.id.editText2)
        var save : Button = findViewById(R.id.button)
        isAdd = intent.getBooleanExtra("ADD", true);
        id = intent.getIntExtra("ID", 0)

        if (!isAdd) {
            var user = helper.getParticularUserData("" + id)
            name.setText(user.Discription)
            number.setText("" + user.phone)
        }

        addData()
    }

    fun validate(): Boolean {
        if (editText.text.isEmpty()) {
            Toast.makeText(this@Contect_Us, "Enter Discription", Toast.LENGTH_SHORT).show()
            return false
        }  else if (editText2.text.isEmpty()) {
            Toast.makeText(this@Contect_Us, "Enter Contect Number", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun addData() {
        button.setOnClickListener {
            if (validate()) {
                if(isAdd) {
                    helper.insertData(editText.text.toString(),
                        editText2.text.toString())
                }else{
                    helper.updateData(""+id,editText.text.toString(),
                        editText2.text.toString())
                }
                clearAllFields()
                finish()
            }
        }
    }


    fun clearAllFields() {
        editText.text.clear();
        editText2.text.clear();
    }

}
