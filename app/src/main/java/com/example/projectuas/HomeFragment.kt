package com.example.projectuas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference

class HomeFragment : Fragment() {
    //recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var makananAdapter: FoodListAdapter
    private lateinit var databaseReference: DatabaseReference
    private lateinit var foodlist: MutableList<Makanan>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        foodlist = mutableListOf()
        makananAdapter = FoodListAdapter(foodlist, fragment = requireActivity().supportFragmentManager, requireContext())
        recyclerView.adapter = makananAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("masakan")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodlist.clear()
                for (dataSnapshot in snapshot.children) {
                    val resep = dataSnapshot.getValue(Makanan::class.java)
                    resep?.let {
                        foodlist.add(it)
                    }
                }
                makananAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("Firebase Database", "Error: ${error.message}")
                Toast.makeText(context, "Error fetching data from database", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
    private fun navigateToDetailResepFragment(masakanId: String) {
        val detailResepFragment = FragmentDetailResep()

        // Mengirim data menggunakan Bundle
        val bundle = Bundle()
        bundle.putString("masakanId", masakanId)
        detailResepFragment.arguments = bundle

        // Ganti fragment di dalam activity
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, detailResepFragment)
            .addToBackStack(null)
            .commit()
    }

}
