package com.example.projectuas

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storageReference: StorageReference
    private lateinit var TVEmail: TextView
    private lateinit var logoProfil: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        auth = FirebaseAuth.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Initialize views
        TVEmail = view.findViewById(R.id.TVEmail)
        logoProfil = view.findViewById(R.id.logoProfil)

        // Set user information if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            TVEmail.text = email ?: "pengguna@gmail.com"
            // Load the image from SharedPreferences
            loadImageFromPrefs()
        }

        // Set click listener for the Edit Profile button
        view.findViewById<Button>(R.id.BTNEditProfile).setOnClickListener {
            openGallery()
        }

        view.findViewById<Button>(R.id.BTNKeluar).setOnClickListener {
            signOut()
        }
        //change pass
        view.findViewById<Button>(R.id.BTNUbahPassword).setOnClickListener {
            showChangePasswordDialog()
        }
        return view
    }

    // Open the gallery to choose an image
    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    // Handle the result from the gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            uploadImage()
        }
    }

    // Upload the selected image to Firebase Storage
    private fun uploadImage() {
        if (filePath != null) {
            val ref = storageReference.child("images/" + auth.currentUser!!.uid + "/profile.jpg")
            val uploadTask = ref.putFile(filePath!!)

            uploadTask.addOnSuccessListener { taskSnapshot ->
                // Get the image URL after the upload is complete
                ref.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    saveImageUrlToPrefs(imageUrl)
                    // Display the updated image in ImageView
                    loadImageFromPrefs()
                }
            }.addOnFailureListener {
                // Handle unsuccessful uploads
                // You can add error handling code here
            }
        }
    }

    // Save the image URL to SharedPreferences
    private fun saveImageUrlToPrefs(imageUrl: String) {
        val editor = sharedPreferences.edit()
        editor.putString("profileImageUrl", imageUrl)
        editor.apply()
    }

    private fun signOut() {
        auth.signOut()

        // Redirect to the login page after signing out
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()  // Optional: Close the current activity
    }

    // Load the image from SharedPreferences and display it
    private fun loadImageFromPrefs() {
        val imageUrl = sharedPreferences.getString("profileImageUrl", "")
        Log.d("MyApp", "Image URL from SharedPreferences: $imageUrl")
        if (!imageUrl.isNullOrEmpty()) {
            // Load the image into ImageView using a library like Glide or Picasso
            // Here, I'm using Glide as an example. Make sure to add the Glide dependency to your app-level build.gradle
            Glide.with(requireContext())
                .load(imageUrl)
                .circleCrop() // Ini akan membuat gambar menjadi bentuk lingkaran
                .into(logoProfil)
        }
    }
    private fun showChangePasswordDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_changepass, null)
        val etCurrentPassword = dialogView.findViewById<EditText>(R.id.current_pass)
        val etNewPassword = dialogView.findViewById<EditText>(R.id.new_pass)
        val etConfirmNewPassword = dialogView.findViewById<EditText>(R.id.confirm_newpass)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Ubah Password")
            .setView(dialogView)
            .setPositiveButton("Ubah") { _, _ ->
                // Handle password change here
                val currentPassword = etCurrentPassword.text.toString()
                val newPassword = etNewPassword.text.toString()
                val confirmNewPassword = etConfirmNewPassword.text.toString()

                // Perform password change using the provided details
                handleChangePassword(currentPassword, newPassword, confirmNewPassword)
            }
            .setNegativeButton("Batal", null)
            .create()

        dialog.show()
    }
    private fun handleChangePassword(currentPassword: String, newPassword: String, confirmNewPassword: String) {
        // Implement your password change logic here
        // Check if the new password and confirm password match
        if (newPassword == confirmNewPassword) {
            // Change password in Firebase
            val user = auth.currentUser
            // Check if the user is not null
            user?.let {
                val credential = auth.signInWithEmailAndPassword(it.email!!, currentPassword)
                credential.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Password change successful
                        user.updatePassword(newPassword)
                            .addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful) {
                                    // Password updated successfully
                                    // Display a success message or perform additional actions
                                    showToast("Password updated successfully")
                                } else {
                                    // Handle password update failure
                                    showToast("Failed to update password")
                                }
                            }
                    } else {
                        // Handle incorrect current password
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // Incorrect current password
                            showToast("Incorrect current password")
                        }
                    }
                }
            }
        } else {
            // Handle password mismatch
            showToast("Passwords do not match")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}



