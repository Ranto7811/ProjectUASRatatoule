package com.example.projectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projectuas.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.NoAccountTextView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            //validasi email
            if(email.isEmpty()){
                binding.emailEditText.error = "Email Harus Diisi"
                binding.emailEditText.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak sesuai
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailEditText.error = "Email Tidak Valid"
                binding.emailEditText.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if(password.isEmpty()){
                binding.passwordEditText.error = "Password Harus Diisi"
                binding.passwordEditText.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }

        //menghapus title project pada bagian atas
        supportActionBar?.hide()

//        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
//        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
//        val loginButton = findViewById<Button>(R.id.loginButton)
//        val noAccount = findViewById<TextView>(R.id.NoAccountTextView)
//
//        loginButton.setOnClickListener{
//            val username = usernameEditText.text.toString()
//            val password = passwordEditText.text.toString()
//
//            if (username == "ranto" && password == "ranto") {
//                val pesan = "Berhasil login"
//                showToast(pesan)
//            } else {
//                val pesan = "Login gagal"
//                showToast(pesan)
//            }
//        }
//
//        noAccount.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
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

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
}