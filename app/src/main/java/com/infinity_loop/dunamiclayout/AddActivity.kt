package com.infinity_loop.dunamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_paytm.*

    class AddActivity : AppCompatActivity() {
        internal var helper = DatabaseHelper(this)
        var isAdd: Boolean = false;
        var id: Int = 0;

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_paytm)
            val actionbar = supportActionBar
            actionbar!!.title = "Add payTm Account"
            var name : EditText = findViewById(R.id.editTextTextPersonName)
            var number :EditText = findViewById(R.id.editTextTextpayTmNumber)
            var save : Button = findViewById(R.id.save)
            var deleteButton : Button = findViewById(R.id.delete)
            isAdd = intent.getBooleanExtra("ADD", true);
            id = intent.getIntExtra("ID", 0)

            if (!isAdd) {
                var user = helper.getParticularUserData("" + id)
                name.setText(user.name)
                number.setText("" + user.phone)
                deleteButton.setVisibility(View.VISIBLE)
            }

            addData()
        }

        fun validate(): Boolean {
            if (editTextTextPersonName.text.isEmpty()) {
                Toast.makeText(this@AddActivity, "Enter Name", Toast.LENGTH_SHORT).show()
                return false
            }  else if (editTextTextpayTmNumber.text.isEmpty()) {
                Toast.makeText(this@AddActivity, "Enter Phone", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        fun addData() {
            save.setOnClickListener {
                if (validate()) {
                    if(isAdd) {
                        helper.insertData(editTextTextPersonName.text.toString(),
                            editTextTextpayTmNumber.text.toString())
                    }else{
                        helper.updateData(""+id,editTextTextPersonName.text.toString(),
                            editTextTextpayTmNumber.text.toString())
                    }
                    clearAllFields()
                    finish()
                }
            }
            delete.setOnClickListener {

                helper.deleteData(""+id)
            }
        }


        fun clearAllFields() {
            editTextTextPersonName.text.clear();
            editTextTextpayTmNumber.text.clear();
        }
    }
