package com.karo.mapsapp

import com.google.firebase.firestore.GeoPoint

class Item (val name: String?=null, val category: List<String>?=null,val logoImageURL:String?=null, val hours:String?=null ,val localization: GeoPoint?=null, val description: String?=null)
{









/*
    fun createItemObj(): Map<String, Any> {
        val messageObj = HashMap<String, Any>()
        messageObj["name"] = name
        messageObj["category"] = category
        messageObj["mapGEO"] = mapGEO
        messageObj["description"] = description
        return messageObj
    }
    constructor(this.name,)
    {
        Item()
    }*/
    override fun toString(): String {
    return name ?: "fail"
}

}

