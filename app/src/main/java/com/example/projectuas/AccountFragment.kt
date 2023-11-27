package com.example.projectuas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // Pemanggilan findViewById dan inisialisasi variabel
//        val myButton = view.findViewById<Button>(R.id.)
        val logoImageView = view.findViewById<ImageView>(R.id.logoImageView)
        val resepTextView = view.findViewById<TextView>(R.id.resep)
        val tersimpanTextView = view.findViewById<TextView>(R.id.tersimpan)
        val favouriteTextView = view.findViewById<TextView>(R.id.favourite)
        val logoutTextView = view.findViewById<TextView>(R.id.logout)
//        val penggunaTextView = view.findViewById<TextView>(R.id.NamaPengguna)
//        val imageView = view.findViewById<ImageView>(R.id.gambar)

        // Lakukan inisialisasi atau manipulasi tampilan di sini, setelah tampilan tercipta

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
