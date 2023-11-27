package com.example.projectuas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    //recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var makananAdapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        val foodList = listOf(
            Makanan("Cireng", R.drawable.cireng),
            Makanan("Perkedel", R.drawable.perkedel),
            Makanan("Orak-Arik Tempe", R.drawable.tmpe),
            Makanan("Omellet", R.drawable.cireng),
            Makanan("Ayam Suwir", R.drawable.ayamsuwi)
            // Tambahkan lebih banyak makanan jika diperlukan
        )
        makananAdapter = FoodListAdapter(foodList)
        recyclerView.adapter = makananAdapter
        return view
    }

}