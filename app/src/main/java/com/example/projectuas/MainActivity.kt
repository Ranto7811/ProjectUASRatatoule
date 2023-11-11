package com.example.projectuas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var makananAdapter: FoodListAdapter
    private val foodList = listOf(
        Makanan("Cireng", R.drawable.cireng),
        Makanan("Perkedel", R.drawable.perkedel),
        Makanan("Orak-Arik Tempe", R.drawable.tmpe),
        Makanan("Omellet", R.drawable.cireng),
        Makanan("Ayam Suwir", R.drawable.ayamsuwi)
        // Tambahkan lebih banyak makanan jika diperlukan
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase


        //menghapus title project pada bagian atas
        supportActionBar?.hide()

        //recycleview
        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        makananAdapter = FoodListAdapter(foodList)
        recyclerView.adapter = makananAdapter
    }
}