package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.TextView

class DogActivity : AppCompatActivity() {
    internal lateinit var name: TextView
    internal lateinit var city: TextView
    internal lateinit var description: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        name = findViewById(R.id.nameDogProfile)
        name.text = intent.getStringExtra("name")!!
        city = findViewById(R.id.cityDogProfile)
        city.text = intent.getStringExtra("city")!!
        description = findViewById(R.id.descDogProfile)
        description.text = intent.getStringExtra("description")!!
    }
}