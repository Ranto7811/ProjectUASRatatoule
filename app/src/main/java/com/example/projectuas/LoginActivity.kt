package com.example.projectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //menghapus title project pada bagian atas
        supportActionBar?.hide()

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val noAccount = findViewById<TextView>(R.id.NoAccountTextView)

        loginButton.setOnClickListener{
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "ranto" && password == "ranto") {
                val pesan = "Berhasil login"
                showToast(pesan)
            } else {
                val pesan = "Login gagal"
                showToast(pesan)
            }
        }

        noAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    true
                }
                R.id.add -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }
                R.id.account -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    true
                }
                else -> false
            }
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}