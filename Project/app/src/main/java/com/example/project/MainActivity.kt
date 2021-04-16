package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gabriela_proszczuk_czw_9_30.DataHolder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataHolder.prepare(applicationContext)
    }

    fun toMap(view: View) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }
    fun toList(view: View) {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}