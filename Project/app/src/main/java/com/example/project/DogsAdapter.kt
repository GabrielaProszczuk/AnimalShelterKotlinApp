package com.example.gabriela_proszczuk_czw_9_30

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DogActivity
import com.example.project.R

class DogsAdapter(var dataSet: Array<Dog>, val context: Context): RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val city: TextView
        val image: ImageView
        init {
            name = view.findViewById(R.id.nameTextView)
            city = view.findViewById(R.id.cityTextView)
            image = view.findViewById(R.id.imageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dogs_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dog = dataSet[position]
        viewHolder.itemView.setOnClickListener{ goToDetails()}
    }

    private fun goToDetails() {
        val intent = Intent(context, DogActivity::class.java)
        context.startActivity(intent)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}



