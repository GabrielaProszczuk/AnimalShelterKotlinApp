package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DogActivity : AppCompatActivity() {
    internal lateinit var name: TextView
    internal lateinit var city: TextView
    internal lateinit var description: TextView
    internal lateinit var imageUrl: String
    internal lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        name = findViewById(R.id.nameDogProfile)
        name.text = intent.getStringExtra("name")!!
        city = findViewById(R.id.cityDogProfile)
        city.text = intent.getStringExtra("city")!!
        description = findViewById(R.id.descDogProfile)
        description.text = intent.getStringExtra("description")!!
        imageUrl = intent.getStringExtra("image")!!
        image = findViewById(R.id.imageDogProfile)
        Picasso.get().load(imageUrl).into(image)
    }

    fun toAdopt(view: View) {
        val intent = Intent(this, AdoptActivity::class.java)
        startActivity(intent)
    }
}