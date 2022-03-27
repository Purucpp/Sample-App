package com.yesandroid.tree.RecyvlerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yesandroid.tree.R

class ThirdAdapter(val iddd: List<String>, val age:List<String>,val itime:List<String>) :
    RecyclerView.Adapter<ThirdAdapter.FlowerViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idText: TextView = itemView.findViewById(R.id.iid)

        private val ageTExt: TextView = itemView.findViewById(R.id.iage)
        private val timetext: TextView = itemView.findViewById(R.id.itime)

        fun bind(word: String, s: String,t:String) {
            idText.text = "ID: "+word
            ageTExt.text="Age: "+s
            timetext.text=t
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw, parent, false)

        return FlowerViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return itime.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(iddd[position],age[position],itime[position])
    }
}