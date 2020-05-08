package com.example.mortargui

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            if(validate() == true && editText1.text.toString().equals("benstump@gmail.com") && editText2.text.toString().equals("donproper")){
                startActivity(Intent(this, Sample_Activity::class.java))
            }
            else{
                Toast.makeText(this@MainActivity, "Failed Login", Toast.LENGTH_SHORT).show()
            }
        }





        resetButton.setOnClickListener {
            editText1.text.clear()
            editText2.text.clear()
        }


        AndroidNetworking.initialize(applicationContext)


    }
    fun validate(): Boolean {
        var valid = true

        val email = editText1.text.toString()
        val password = editText2.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText1.error = "enter a valid email address"
            valid = false
        } else{
            editText1.error = null
        }


        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            editText2.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else  {
            editText2.error = null
        }

        return valid
    }




}


