package com.example.projectuas

import android.accounts.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //recyclerview
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var makananAdapter: FoodListAdapter
//    private val foodList = listOf(
//        Makanan("Cireng", R.drawable.cireng),
//        Makanan("Perkedel", R.drawable.perkedel),
//        Makanan("Orak-Arik Tempe", R.drawable.tmpe),
//        Makanan("Omellet", R.drawable.cireng),
//        Makanan("Ayam Suwir", R.drawable.ayamsuwi)
//        // Tambahkan lebih banyak makanan jika diperlukan
//    )

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.add -> {
                loadFragment(AddFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.account -> {
                loadFragment(AccountFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase


        //menghapus title project pada bagian atas
        supportActionBar?.hide()

        //recycleview
//        recyclerView = findViewById(R.id.rv)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        makananAdapter = FoodListAdapter(foodList)
//        recyclerView.adapter = makananAdapter

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        // Memuat fragmen pertama saat aplikasi pertama kali dijalankan
        loadFragment(HomeFragment())
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}