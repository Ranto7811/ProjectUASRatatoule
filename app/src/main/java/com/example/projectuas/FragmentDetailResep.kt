package com.example.projectuas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.projectuas.model.Masakan
import com.google.firebase.database.*

class FragmentDetailResep : Fragment() {

        private lateinit var databaseReference: DatabaseReference
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resep, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
        val judul:TextView = view.findViewById(R.id.tv_judulresep)
        val image:ImageView = view.findViewById(R.id.iv_imgdetail)
        val bahan:TextView = view.findViewById(R.id.tv_bahan)
        val langkah:TextView = view.findViewById(R.id.tv_carabuat)
        // ambil data dari adapter
        val juduldetail =arguments?.getString("judul")
        val imagedetail =arguments?.getString("gambar")
        val bahandetail =arguments?.getString("bahan")
        val langkahdetail =arguments?.getString("langkah")
        judul.text = juduldetail
        bahan.text = bahandetail
        langkah.text = langkahdetail
        Glide.with(requireContext()).load(imagedetail).placeholder(R.drawable.cireng).into(image)

    }
}


