package com.example.project

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.gabriela_proszczuk_czw_9_30.DataHolder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class MapActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            //googleMap.isMyLocationEnabled = true
            val location = LatLng(52.13,21.0)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))
            makeRequest()
        })
    }
    private fun makeRequest(){
        val queue = DataHolder.queue
        val url = "https://adoptdogsapi.herokuapp.com/api/v1/dogs/"
        val dogListRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    response ->
                   // println(response)
                    if(flag){
                        loadData(response)
                        flag = false
                    }

                },
                Response.ErrorListener {
                    TODO()
                })
        queue.add(dogListRequest)
    }

    private fun loadData(response: JSONArray) {
        response.let {
            val dogsCount = response.length()
            val tmpData = arrayOfNulls<JSONObject>(dogsCount)
            println(dogsCount)
            for (i in 0 until dogsCount) {
                tmpData[i] = response.getJSONObject(i)
                //println(tmpData[i])
            }

            val list = tmpData.distinctBy { Pair(it?.get("city"), it?.get("street")) }
            var markers: Array<Marker> = emptyArray()
            for(i in 0 until list.size){

                //val name = response.getJSONObject(i).getString("name")
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocationName("Poland " + list[i]?.get("city").toString() + " " + list[i]?.get("street").toString(), 1)
                val address: Address = addresses[0]
                val longitude: Double = address.getLongitude()
                val latitude: Double = address.getLatitude()
                println(latitude)
                println(longitude)
                val location = LatLng(latitude,longitude)
                val mkr = googleMap.addMarker(MarkerOptions().position(location).title(list[i]?.get("city").toString() + " " + list[i]?.get("street").toString()))
                mkr.tag = i

                //println(list[i])
            }
                googleMap.setOnMarkerClickListener {
                    marker ->
                    val intent = Intent(this, MapToListActivity::class.java).apply{
                        val info = marker.title.split(" ")
                        println(info)
                        putExtra("city", info[0])
                        putExtra("street", info[1]+" "+info[2])
                    }
                    startActivity(intent)
                    true
                }

        }
        }
}