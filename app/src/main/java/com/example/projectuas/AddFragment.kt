package com.example.projectuas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth


class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            // Redirect ke halaman login jika belum login
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()  // Optional: Tutup aktivitas saat ini
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAdd = view.findViewById<Button>(R.id.button_upload)

        auth = FirebaseAuth.getInstance()

        buttonAdd.setOnClickListener{
            if (auth.currentUser == null) {
                // Redirect ke halaman login jika belum login
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()  // Optional: Tutup aktivitas saat ini
            }
        }
    }
}