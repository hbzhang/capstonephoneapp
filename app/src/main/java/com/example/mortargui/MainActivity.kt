package com.example.mortargui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val url2 = "http://144.75.191.68:5005/getusername"

        var  userName =""


        val url3 = "http://144.75.191.68:5005/getpassword"

        var password =""


        var client = OkHttpClient()
        var request = Request.Builder()
            .url(url2)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val myResponse = response.body()!!.string()
                    Log.d("server response", myResponse)
                    userName = myResponse;

                    request = Request.Builder()
                        .url(url3)
                        .build()
                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            e.printStackTrace()
                        }

                        @Throws(IOException::class)
                        override fun onResponse(call: Call, response: Response) {
                            if (response.isSuccessful) {
                                val myResponse = response.body()!!.string()
                                Log.d("server response", myResponse)
                                password = myResponse;

                            }
                        }
                    })

                }
            }
        })

        loginButton.setOnClickListener {

            var user = editText1.text.trim().toString()
            var pass = editText2.text.trim().toString()

            if(user=="benstump@gmail.com" && pass=="donproper"){
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

    override fun onStart() {
        super.onStart()


        // start service
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


