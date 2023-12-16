package com.example.projectuas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.example.projectuas.databinding.FragmentAddBinding
import com.example.projectuas.model.Masakan
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.antlr.v4.runtime.misc.MurmurHash.finish


class AddFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAddBinding
    private var image: Uri?=null
    private lateinit var judul:String
    private lateinit var bahan:String
    private lateinit var langkah:String

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()

        binding.buttonUpload.setOnClickListener{
            judul = binding.ETJudul.text.toString().trim()
            bahan = binding.ETBahan.text.toString().trim()
            langkah = binding.ETLangkah.text.toString().trim()

            if(!(judul.isBlank()||bahan.isBlank()||langkah.isBlank())){
                uploadData()
//                Toast.makeText(requireContext(), "Ber", Toast.LENGTH_SHORT).show()
//                finish()
            }
        }

        binding.selectImage.setOnClickListener{
            pickImage.launch("image/*")
        }

        return binding.root


    }

    private fun uploadData() {
        val menuRef = database.getReference("masakan")
        val newItemKey = menuRef.push().key

        if(image!=null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("masakan_images/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(image!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl->

                    val newItem=Masakan(
                        judul = judul,
                        bahan = bahan,
                        langkah = langkah,
                        image = downloadUrl.toString()
                    )
                    newItemKey?.let{
                        key->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(requireContext(), "Berhasil upload resep", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener{
                                Toast.makeText(requireContext(), "Gagal upload resep", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }
                .addOnFailureListener{
                    Toast.makeText(requireContext(), "image upload failed", Toast.LENGTH_SHORT).show()
                }
        }else{
            Toast.makeText(requireContext(), "Tidak ada image yang dipilih", Toast.LENGTH_SHORT).show()
        }
    }


    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if (uri != null){
            binding.selectImage.setImageURI(uri)
            image =uri
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
}