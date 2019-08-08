package com.karo.mapsapp


import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var title = "Aplikacja"
        (activity as AppCompatActivity).supportActionBar?.title = title
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.categories_Btn).setOnClickListener (this)

    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.categories_Btn -> navController!!.navigate(R.id.action_mainFragment_to_categoriesFragment)

        }
    }
}
