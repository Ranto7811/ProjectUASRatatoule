package com.example.projectuas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodListAdapter(private val foodList: List<Makanan>) :
RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TitleResep: TextView = itemView.findViewById(R.id.tv_food_home)
        val imageResep: ImageView = itemView.findViewById(R.id.iv_food_home)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_home, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val makanan = foodList[position]
        holder.TitleResep.text = makanan.resep

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}

