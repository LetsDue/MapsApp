package com.karo.mapsapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_presentation.*
import org.w3c.dom.Text


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PresentationFragment : Fragment() {
    val locationArrayMap:HashMap<String,String> = HashMap<String,String>()
    var name:String="fail"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments!!.getString("name")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_presentation, container, false)
        var title="ItemView"
        (activity as AppCompatActivity).supportActionBar?.title = title
        var textView:TextView = view.findViewById(R.id.itemText)
        textView.text = name
        locationArrayMap["lotnisko"] = "52.563122,19.711969"
        locationArrayMap["ratusz"] = "52.545288,19.684685"
        locationArrayMap["zoo"] = "52.536772,19.700291"
        var mapBtn:ImageButton= view.findViewById(R.id.mapBtn)
        mapBtn.setOnClickListener()
        {
            val label = name
            val location = locationArrayMap[name]
            val uriBegin = "geo:$location"
            val query = "$location($label)"
            val encodedQuery = Uri.encode(query)
            val uriString = "$uriBegin?q=$encodedQuery"
            val uri = Uri.parse(uriString)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
        return view
    }

}
