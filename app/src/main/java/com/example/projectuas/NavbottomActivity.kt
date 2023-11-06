package com.example.projectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbottomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbottom)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> {
//                    startActivity(Intent(this, RegisterActivity::class.java))
//                    true
//                }
//                R.id.add -> {
//                    startActivity(Intent(this, LoginActivity::class.java))
//                    true
//                }
//                R.id.account -> {
//                    startActivity(Intent(this, RegisterActivity::class.java))
//                    true
//                }
//                else -> false
//            }
//        }
    }
}