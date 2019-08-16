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


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var myClipboard: ClipboardManager
    private lateinit var clickTextView: TextView
    private lateinit var clickTextViewE: TextView
    private lateinit var imageViewCategory: ImageView
    private val db = FirebaseFirestore.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CategoryList = mutableListOf()
        addDatabaseListener()
        var toast: Toast =  Toast.makeText(context,  getString(R.string.EmailCopied), Toast.LENGTH_SHORT)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        clickTextView = view.findViewById(R.id.phoneText)
        clickTextView.setOnClickListener()
        {
            val myClip: ClipData = ClipData.newPlainText("number", phoneText.text)
            myClipboard.primaryClip =myClip
            toast.cancel()
            toast= Toast.makeText(context, getString(R.string.NumberCopied), Toast.LENGTH_SHORT)
            toast.show()
        }
        clickTextViewE = view.findViewById(R.id.emailText)
        clickTextViewE.setOnClickListener()
        {
            val myClip = ClipData.newPlainText("text", emailText.text)
            myClipboard.primaryClip =myClip

            toast.cancel()
            toast= Toast.makeText(context, getString(R.string.EmailCopied), Toast.LENGTH_SHORT)
            toast.show()
        }
        imageViewCategory = view.findViewById(R.id.categoriesIcon)
        imageViewCategory.setOnClickListener()
        {
            if(!ItemsList.isNullOrEmpty()) {
                navController.navigate(R.id.action_mainFragment_to_categoriesFragment)
            }else
            {
                Toast.makeText(context, getString(R.string.ConnectionErrorString), Toast.LENGTH_SHORT).show()
            }
        }


        //val textView = view.findViewById<TextView>(R.id.textView)
        val searchIcon = view.findViewById<ImageView>(R.id.searchIcon)

        searchIcon.setOnClickListener()
        {
            if(!ItemsList.isNullOrEmpty()) {
                navController.navigate(R.id.action_mainFragment_to_searchFragment)
            }else
            {
                Toast.makeText(context, getString(R.string.ConnectionErrorString), Toast.LENGTH_SHORT).show()
            }

        }


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = getString(R.string.AppNameString)
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
