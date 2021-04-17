package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.gabriela_proszczuk_czw_9_30.DataHolder
import com.example.gabriela_proszczuk_czw_9_30.Dog
import com.example.gabriela_proszczuk_czw_9_30.DogsAdapter
import org.json.JSONArray

class ListActivity : AppCompatActivity() {
    internal lateinit var dogsRecycler: RecyclerView
    internal lateinit var adapter: DogsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        dogsRecycler = findViewById(R.id.dogsRecycler)
        dogsRecycler.layoutManager = LinearLayoutManager(this)
        adapter = DogsAdapter(emptyArray(), this)
        dogsRecycler.adapter = adapter
        makeRequest()
        //adapter.dataSet = loadData()
        //adapter.notifyDataSetChanged()

    }

    private fun makeRequest(){
        val queue = DataHolder.queue
        val url = "https://adoptdogsapi.herokuapp.com/api/v1/dogs/"
        val dogListRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                    response ->
                //println(response)
               adapter.dataSet = loadData(response)
               adapter.notifyDataSetChanged()

            },
            Response.ErrorListener {
                TODO()
            })
        queue.add(dogListRequest)
    }

    private fun loadData(response: JSONArray?): Array<Dog> {
        response?.let{
            val dogsCount = response.length()
            val tmpData = arrayOfNulls<Dog>(dogsCount)
            for(i in 0 until dogsCount){
                val name = response.getJSONObject(i).getString("name")
                val city = response.getJSONObject(i).getString("city")
                val description = response.getJSONObject(i).getString("description")
                val imageUrl = response.getJSONObject(i).getString("photo")
                val dogObject = Dog(name, city, description, imageUrl)

                tmpData[i]=dogObject
            }
            return tmpData as Array<Dog>
        }
        return emptyArray()
    }
}