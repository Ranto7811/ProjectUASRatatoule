package com.example.projectuas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        auth = FirebaseAuth.getInstance()
        view.findViewById<View>(R.id.BTNKeluar).setOnClickListener{
            signOut()
        }
        // Pemanggilan findViewById dan inisialisasi variabel
//        val myButton = view.findViewById<Button>(R.id.)
//        val logoImageView = view.findViewById<ImageView>(R.id.logoImageView)
//        val resepTextView = view.findViewById<TextView>(R.id.resep)
//        val tersimpanTextView = view.findViewById<TextView>(R.id.tersimpan)
//        val favouriteTextView = view.findViewById<TextView>(R.id.favourite)
//        val logoutTextView = view.findViewById<TextView>(R.id.logout)
//        val penggunaTextView = view.findViewById<TextView>(R.id.NamaPengguna)
//        val imageView = view.findViewById<ImageView>(R.id.gambar)

        // Lakukan inisialisasi atau manipulasi tampilan di sini, setelah tampilan tercipta

        return view
    }

    private fun signOut() {
        auth.signOut()

        // Redirect ke halaman login setelah keluar
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()  // Optional: Tutup aktivitas saat ini
    }

}
