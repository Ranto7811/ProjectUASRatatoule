package com.example.projectuas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.projectuas.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.RegisterButton.setOnClickListener{
            val email = binding.EmailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            //validasi email
            if(email.isEmpty()){
                binding.EmailEditText.error = "Email Harus Diisi"
                binding.EmailEditText.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak sesuai
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.EmailEditText.error = "Email Tidak Valid"
                binding.EmailEditText.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if(password.isEmpty()){
                binding.passwordEditText.error = "Password Harus Diisi"
                binding.passwordEditText.requestFocus()
                return@setOnClickListener
            }

            //validasi panjang password
            if (password.length < 6){
                binding.passwordEditText.error = "Password Minimal 6 Karakter"
                binding.passwordEditText.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,password)
        }

    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Berhasil Daftar Akun", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    it.exception?.message?.let { it1 -> Log.d("Login", it1) }
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}