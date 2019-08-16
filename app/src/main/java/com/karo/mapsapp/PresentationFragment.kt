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
import com.bumptech.glide.Glide
import android.content.pm.PackageManager
import androidx.core.view.isGone


class PresentationFragment : Fragment() {
    var name:String="fail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("name") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_presentation, container, false)
        //val title="ItemView"

        val logoView=view.findViewById<ImageView>(R.id.logoItemsView)
        val logoBigView=view.findViewById<ImageView>(R.id.logoBigView)

        //(activity as AppCompatActivity).supportActionBar?.title = title
        val textView:TextView = view.findViewById(R.id.itemText)
        textView.text = name

        val mapBtn:ImageButton= view.findViewById(R.id.mapBtn)
        mapBtn.setOnClickListener()
        {
            val label = name
            val item: Item? = ItemsList?.find { it.name == name }
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
        val facebookImage: ImageView = view.findViewById(R.id.facebookImage)
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
        //val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("logos").child("Lotnisko.gif")
        getLogo(logoView, logoBigView)



        //godziny
        val itemH:Item = ItemsList?.find { it.name == name }!!
        val hoursView:TextView = view.findViewById(R.id.hoursView)
        if(!itemH.hours.isNullOrEmpty()) {
            val hours =
                """
        ${itemH.hours[0]}
        ${itemH.hours[1]}
        ${itemH.hours[2]}
        ${itemH.hours[3]}
        ${itemH.hours[4]}
        ${itemH.hours[5]}
        ${itemH.hours[6]}
    """.trimIndent()
            hoursView.text = hours
        }else
        {
            val hoursNamesView: TextView = view.findViewById(R.id.hoursNamesView)
            hoursView.isGone = true
            hoursNamesView.isGone = true
        }

        var phonesString =""
        val phonesView: TextView = view.findViewById(R.id.phonesView)
        if(!itemH.phones.isNullOrEmpty()) {
            itemH.phones.forEach { str ->
                phonesString = phonesString + str + "\n"
            }
            phonesView.text = phonesString
        }else{
            val phoneLogo:ImageView = view.findViewById(R.id.phoneLogo)
            phonesView.isGone = true
            phoneLogo.isGone = true
        }

        val descriptionView: TextView = view.findViewById(R.id.descriptionView)
        if(!itemH.description.isNullOrEmpty()) {
            descriptionView.text = itemH.description
        }else{
            descriptionView.isGone = true
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

        val facebookURL = ItemsList?.find{it.name==name}?.facebookURL
        val facebookPageID = ItemsList?.find{it.name==name}?.facebookPageID
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



