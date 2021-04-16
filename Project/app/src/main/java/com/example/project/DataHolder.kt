package com.example.gabriela_proszczuk_czw_9_30

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley.newRequestQueue

object DataHolder {
    lateinit var queue: RequestQueue

    fun prepare(context: Context){
        queue = newRequestQueue(context)
    }
}