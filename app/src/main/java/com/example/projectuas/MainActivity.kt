package com.example.projectuas

import android.accounts.Account
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class
MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.add -> {
                    // Memeriksa apakah pengguna sudah login
                    if (isUserLoggedIn()) {
                        loadFragment(AddFragment())
                    } else {
                        // Redirect ke halaman login jika belum login
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    // Memeriksa apakah pengguna sudah login
                    if (isUserLoggedIn()) {
                        loadFragment(AccountFragment())
                    } else {
                        // Redirect ke halaman login jika belum login
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi objek FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Menghapus title project pada bagian atas
        supportActionBar?.hide()

        // Recycleview (jika diperlukan)
        // recyclerView = findViewById(R.id.rv)
        // recyclerView.layoutManager = LinearLayoutManager(this)
        // makananAdapter = FoodListAdapter(foodList)
        // recyclerView.adapter = makananAdapter

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

    // Fungsi untuk memeriksa apakah pengguna sudah login
    private fun isUserLoggedIn(): Boolean {
        val currentUser: FirebaseUser? = auth.currentUser
        return currentUser != null
    }
}