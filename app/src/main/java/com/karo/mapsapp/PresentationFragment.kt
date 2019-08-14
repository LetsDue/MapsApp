package com.karo.mapsapp


import android.content.Context
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
import android.content.pm.PackageManager
import androidx.core.view.isGone
import kotlinx.android.synthetic.main.fragment_presentation.*


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
        var facebookImage: ImageView = view.findViewById(R.id.facebookImage)
        if(ItemsList?.find{it.name==name}?.facebookURL.isNullOrEmpty() || ItemsList?.find{it.name==name}?.facebookPageID.isNullOrEmpty())
        {
            facebookImage.isGone = true
        }else {
            facebookImage.setOnClickListener()
            {
                val facebookIntent = Intent(Intent.ACTION_VIEW)
                val facebookUrl = getFacebookPageURL(context!!)
                facebookIntent.data = Uri.parse(facebookUrl)
                startActivity(facebookIntent)
            }
        }
        val storageReference = FirebaseStorage.getInstance().reference.child("logos").child("Lotnisko.gif")
        getLogo(logoView, logoBigView)



        //godziny
        var itemH:Item = ItemsList?.find { it?.name == name }!!
        var hoursView:TextView = view.findViewById(R.id.hoursView)
        if(!itemH.hours.isNullOrEmpty()) {
            var hours =
                """
        ${itemH.hours?.get(0)}
        ${itemH.hours?.get(1)}
        ${itemH.hours?.get(2)}
        ${itemH.hours?.get(3)}
        ${itemH.hours?.get(4)}
        ${itemH.hours?.get(5)}
        ${itemH.hours?.get(6)}
    """.trimIndent()
            hoursView.text = hours
        }else
        {
            var hoursNamesView: TextView = view.findViewById(R.id.hoursNamesView)
            hoursView.isGone = true
            hoursNamesView.isGone = true
        }

        var phonesString:String =""
        var phonesView: TextView = view.findViewById(R.id.phonesView)
        if(!itemH.phones.isNullOrEmpty()) {
            itemH.phones!!.forEach { str ->
                phonesString = phonesString + str + "\n"
            }
            phonesView.text = phonesString
        }else{
            var phoneLogo:ImageView = view.findViewById(R.id.phoneLogo)
            phonesView.isGone = true
            phoneLogo.isGone = true
        }

        return view
   }
    private fun getLogo(logoView:ImageView, logoBigView:ImageView)
    {
        if(ItemsList?.find{it.name==name}?.logoBigImageURL.isNullOrEmpty())
        {
            logoBigView.isGone = true
        }
        Glide.with(this)
            .load(ItemsList?.find{it.name==name}?.logoImageURL)
            .into(logoView)
        Glide.with(this)
            .load(ItemsList?.find{it.name==name}?.logoBigImageURL)
            .into(logoBigView)
    }
    private fun getFacebookPageURL(context: Context): String {

        var facebookURL = ItemsList?.find{it.name==name}?.facebookURL
        var facebookPageID = ItemsList?.find{it.name==name}?.facebookPageID
        val packageManager = context.packageManager
        return try {
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$facebookURL"
            } else { //older versions of fb app
                "fb://page/$facebookPageID"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            facebookURL //normal web url
        }!!

    }
}



