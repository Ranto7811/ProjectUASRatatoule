package com.example.projectuas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.model.Masakan
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
        makananAdapter = FoodListAdapter(foodlist)
        recyclerView.adapter = makananAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("masakan")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodlist.clear()

                for (dataSnapshot in snapshot.children) {
                    val resep = dataSnapshot.getValue(Makanan::class.java)
                    resep?.let { foodlist.add(it) }
                }

                makananAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
        return view
    }
}