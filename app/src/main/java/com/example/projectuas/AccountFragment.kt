import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projectuas.R

class AccountFragment : Fragment() {
    private lateinit var myButton: Button
    private var param2: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // Pemanggilan findViewById dan inisialisasi variabel
        val myButton = view.findViewById<Button>(R.id.RegisterButton)
        val logoImageView = view.findViewById<ImageView>(R.id.logoImageView)
        val resepTextView = view.findViewById<TextView>(R.id.resep)
        val tersimpanTextView = view.findViewById<TextView>(R.id.tersimpan)
        val favouriteTextView = view.findViewById<TextView>(R.id.favourite)
        val logoutTextView = view.findViewById<TextView>(R.id.logout)
        val penggunaTextView = view.findViewById<TextView>(R.id.NamaPengguna)
        val imageView = view.findViewById<ImageView>(R.id.profil)

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

    }
}
