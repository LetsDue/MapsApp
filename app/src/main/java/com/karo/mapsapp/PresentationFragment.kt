package com.karo.mapsapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class PresentationFragment : Fragment() {
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

        var logoView=view.findViewById<ImageView>(R.id.logoItemsView)
        var logoBigView=view.findViewById<ImageView>(R.id.logoBigView)

        (activity as AppCompatActivity).supportActionBar?.title = title
        var textView:TextView = view.findViewById(R.id.itemText)
        textView.text = name

        var mapBtn:ImageButton= view.findViewById(R.id.mapBtn)
        mapBtn.setOnClickListener()
        {
            val label = name
            val item: Item? = ItemsList?.find { it?.name == name }
            val localization:String = item?.localization?.latitude.toString()+","+item?.localization?.longitude.toString()
            val uriBegin = "geo:$localization"
            val query = "$localization($label)"
            val encodedQuery = Uri.encode(query)
            val uriString = "$uriBegin?q=$encodedQuery"
            val uri = Uri.parse(uriString)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        val storageReference = FirebaseStorage.getInstance().reference.child("logos").child("Lotnisko.gif")
        getLogo(logoView, logoBigView)
       return view
   }
    private fun getLogo(logoView:ImageView, logoBigView:ImageView)
    {

        Glide.with(this)
            .load(ItemsList?.find{it.name==name}?.logoImageURL)
            .into(logoView)
        Glide.with(this)
            .load(ItemsList?.find{it.name==name}?.logoBigImageURL)
            .into(logoBigView)

    }
}
