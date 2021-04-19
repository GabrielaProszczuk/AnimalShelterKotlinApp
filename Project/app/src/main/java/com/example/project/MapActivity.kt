package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            //googleMap.isMyLocationEnabled = true
            val location = LatLng(52.13,21.0)
            val coordinateX = 52.13
            val coordinateY = 21.0
            googleMap.addMarker(MarkerOptions().position(location).title("Warszawa"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))
            googleMap.setOnMarkerClickListener {
                val intent = Intent(this, MapToListActivity::class.java).apply{
                    putExtra("X", coordinateX)
                    putExtra("Y", coordinateY)
                }
                startActivity(intent)
                true
            }
        })
    }
}