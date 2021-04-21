package com.example.project

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    internal lateinit var searched: String
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
        searched = ""

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main,menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnCloseListener(SearchView.OnCloseListener {
            makeRequest()
            true
        })
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean{
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                val array = adapter.dataSet.filter { it.city.toLowerCase() == query?.toLowerCase() || it.name.toLowerCase() == query?.toLowerCase()}
                adapter.dataSet = array.toTypedArray()
                adapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val new:CharSequence
                if (newText != null) {
                    new = newText
                }else{
                    new = ""
                }
                if(searched.length > new.length){
                    makeRequest()
                }
                val array = adapter.dataSet.filter { it.city.contains(new,true) || it.name.contains(new, true) }
                adapter.dataSet = array.toTypedArray()
                adapter.notifyDataSetChanged()
                if (newText != null) {
                    searched = newText
                }
                return false
            }
        })
        return true
    }

    private fun makeRequest(){
        val queue = DataHolder.queue
        val url = "https://adoptdogsapi.herokuapp.com/api/v1/dogs/"
        val dogListRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                    response ->
                       // println(response)
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
                val street = response.getJSONObject(i).getString("street")
                val description = response.getJSONObject(i).getString("description")
                val imageUrl = response.getJSONObject(i).getString("photo")
                val dogObject = Dog(name, street, city, description, imageUrl)

                tmpData[i]=dogObject
            }
            return tmpData as Array<Dog>
        }
        return emptyArray()
    }
}