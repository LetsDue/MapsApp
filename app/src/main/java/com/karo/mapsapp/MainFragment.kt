package com.karo.mapsapp


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore


import kotlinx.android.synthetic.main.fragment_main.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var myClipboard: ClipboardManager
    lateinit var clickTextView: TextView
    lateinit var clickTextViewE: TextView
    lateinit var imageViewCategory: ImageView
    val db = FirebaseFirestore.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CategoryList = mutableListOf()
        addDatabaseListener()
        var toast: Toast = Toast.makeText(context, "E-mail został skopiowany", Toast.LENGTH_SHORT)
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        clickTextView = view.findViewById(R.id.phoneText)
        clickTextView.setOnClickListener()
        {
            var myClip = ClipData.newPlainText("text", phoneText.text)
            myClipboard?.primaryClip=myClip
            if (toast!= null) {
                toast.cancel()
            }
            toast= Toast.makeText(context, "Numer został skopiowany", Toast.LENGTH_SHORT)
            toast.show()
        }
        clickTextViewE = view.findViewById(R.id.emailText)
        clickTextViewE.setOnClickListener()
        {
            var myClip = ClipData.newPlainText("text", emailText.text)
            myClipboard?.primaryClip=myClip

            if (toast!= null) {
                toast.cancel()
            }
            toast= Toast.makeText(context, "E-mail został skopiowany", Toast.LENGTH_SHORT)
            toast.show()
        }
        imageViewCategory = view.findViewById(R.id.categoriesIcon)
        imageViewCategory.setOnClickListener()
        {
            if(!ItemsList.isNullOrEmpty()) {
                navController!!.navigate(R.id.action_mainFragment_to_categoriesFragment)
            }else
            {
                Toast.makeText(context, "Błąd pobierania, wymagany dostęp do internetu", Toast.LENGTH_SHORT).show()
            }
        }


        val textView = view.findViewById<TextView>(R.id.textView)
        val searchIcon = view.findViewById<ImageView>(R.id.searchIcon)

        searchIcon.setOnClickListener()
        {

        }


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var title = "Aplikacja"
        (activity as AppCompatActivity).supportActionBar?.title = title

        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF242460.toInt()))

        myClipboard = (activity as AppCompatActivity).getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        navController = Navigation.findNavController(view)

// ...


    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.searchIcon ->{
            }
        }
    }

    private fun addDatabaseListener() {
        db.collection("items").addSnapshotListener{snapshot, e ->
            if (e != null) {
                Log.w("Tah", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                ItemsList  = snapshot.toObjects(Item::class.java)
                ItemsList?.forEach { item-> item.category?.forEach { cat ->
                    if(CategoryList?.contains(cat)==false){
                        CategoryList?.add(cat)
                    }
                    }}
                CategoryList?.sort()

            } else {
                Log.d("tag", "Current data: null")
            }
        }

    }
}
