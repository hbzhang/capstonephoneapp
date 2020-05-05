package com.example.mortargui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sign_in.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gotocreatacc.setOnClickListener {
            startActivity(Intent(this, Createaccountactivity::class.java))
        }



        gotosigning.setOnClickListener {
            startActivity(Intent(this, Sample_Activity::class.java))


        }
        AndroidNetworking.initialize(applicationContext)


    }
}
