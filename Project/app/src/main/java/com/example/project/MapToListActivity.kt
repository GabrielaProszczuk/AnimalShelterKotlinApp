package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MapToListActivity : AppCompatActivity() {
    internal lateinit var coordinates: Pair<Double,Double>
    internal lateinit var test: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_to_list)

        test = findViewById(R.id.test)
        test.text = intent.getDoubleExtra("X").toString()
        val x = intent.getDoubleExtra("Y")
        //var x = intent.getStringExtra("X")!!
        //var y = intent.getStringExtra("Y")!!
        //coordinates = Pair(x.toDouble(),y.toDouble())
        //println(coordinates)
    }
}

private fun Intent.getDoubleExtra(s: String): Double {
   return s.toDouble()
}

