package com.karo.mapsapp


import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            navController!!.navigate(R.id.action_mainFragment_to_categoriesFragment)
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

    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
        }
    }
}
