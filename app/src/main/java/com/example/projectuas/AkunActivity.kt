package com.example.projectuas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class AkunActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mendapatkan referensi ke elemen-elemen dalam layout
        val myButton = findViewById<Button>(R.id.myButton)
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)
        val resepTextView = findViewById<TextView>(R.id.resep)
        val tersimpanTextView = findViewById<TextView>(R.id.tersimpan)
        val favouriteTextView = findViewById<TextView>(R.id.favourite)
        val logoutTextView = findViewById<TextView>(R.id.logout)
        val penggunaTextView = findViewById<TextView>(R.id.NamaPengguna)
        val imageView = findViewById<ImageView>(R.id.gambar)

        //menambahkan onClickListener untuk tombol
        myButton.setOnClickListener {
            //kode yang akan dijalankan saat tombol diklik
            Toast.makeText(this, "Tombol diklik", Toast.LENGTH_SHORT).show()
        }


    }
}