package com.example.projectuas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FoodListAdapter(
    private val foodList: List<Makanan>,
    val fragment: FragmentManager,
    val context: Context
): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(makanan: Makanan)
    }
    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleResep: TextView = itemView.findViewById(R.id.tv_food_home)
        val imageResep: ImageView = itemView.findViewById(R.id.iv_food_home)

        init {
            // Tambahkan listener klik di sini
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_home, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val makanan = foodList[position]
        holder.titleResep.text = makanan.judul
        Glide.with(context)
            .load(makanan.image)
            .placeholder(R.drawable.cireng) // placeholder gambar sementara
            .into(holder.imageResep)
        holder.imageResep.setOnClickListener {
            val gambar = foodList[position].image
            val judul = foodList[position].judul
            val bahan = foodList[position].bahan
            val langkah = foodList[position].langkah
            val transaksi = fragment.beginTransaction()
            val bundle = Bundle()
            val fragmentDetailResep = FragmentDetailResep()
            bundle.putString("gambar",gambar)
            bundle.putString("judul",judul)
            bundle.putString("bahan",bahan)
            bundle.putString("langkah",langkah)
            fragmentDetailResep.arguments = bundle
            transaksi.replace(R.id.frame_layout, fragmentDetailResep)
            transaksi.addToBackStack(null)
            transaksi.commit()

        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}

