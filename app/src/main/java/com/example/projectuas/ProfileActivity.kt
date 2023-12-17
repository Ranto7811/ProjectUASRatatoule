package com.example.projectuas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.HashMap

class ProfileActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private lateinit var firestore: FirebaseFirestore
//    private lateinit var storage: FirebaseStorage
//    private lateinit var tvPengguna: TextView
//    private lateinit var tvTanggalLahir: TextView
//    private lateinit var tvKelamin: TextView
//    private lateinit var btnEditProfile: Button
//    private lateinit var storageReference: StorageReference
//    private val PICK_IMAGE_REQUEST = 1
//    private var filePath: Uri? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile)
//
//        auth = FirebaseAuth.getInstance()
//        firestore = FirebaseFirestore.getInstance()
//        storage = FirebaseStorage.getInstance()
//        storageReference = storage.reference
//
//        tvPengguna = findViewById(R.id.tvPengguna)
//        tvTanggalLahir = findViewById(R.id.tvTanggalLahir)
//        tvKelamin = findViewById(R.id.tvKelamin)
//        btnEditProfile = findViewById(R.id.tvEditProfile)
//
//        btnEditProfile.setOnClickListener {
//            // Membuka galeri untuk memilih foto
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
//        }
//
//        tvPengguna.setOnClickListener {
//            // Handle the TextView click to update the user's name in Firebase
//            updateUserData("displayName", "New Name")
//        }
//
//        tvTanggalLahir.setOnClickListener {
//            // Handle the TextView click to update the user's birthdate in Firebase
//            updateUserData("birthdate", "New Birthdate")
//        }
//
//        tvKelamin.setOnClickListener {
//            // Handle the TextView click to update the user's gender in Firebase
//            updateUserData("gender", "New Gender")
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
//            filePath = data.data
//            // Upload the selected image to Firebase Storage
//            uploadImage()
//        }
//    }

//    private fun uploadImage() {
//        if (filePath != null) {
//            val ref = storageReference.child("images/${auth.currentUser?.uid}")
//            ref.putFile(filePath!!)
//                .addOnSuccessListener {
//                    // Image uploaded successfully, now get the download URL
//                    ref.downloadUrl.addOnSuccessListener { uri ->
//                        // Update the user's photoUrl in Firebase Authentication
//                        auth.currentUser?.updateProfile {
//                            it.photoUri = uri
//                        }
//
//                        // Update the user's photoUrl in Firestore
//                        updateUserData("photoUrl", uri.toString())
//                    }
//                }
//                .addOnFailureListener {
//                    // Handle unsuccessful upload
//                }
//        }
//    }

//    private fun updateUserData(field: String, value: String) {
//        val user = auth.currentUser
//
//        // Update the user's data in Firestore
//        user?.uid?.let {
//            val userRef = firestore.collection("users").document(it)
//            val updates = HashMap<String, Any>()
//            updates[field] = value
//
//            userRef.update(updates)
//                .addOnSuccessListener {
//                    // Update successful
//                }
//                .addOnFailureListener {
//                    // Handle the error
//                }
//        }
//    }
}

