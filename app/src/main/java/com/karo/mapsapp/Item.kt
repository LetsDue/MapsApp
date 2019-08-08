package com.karo.mapsapp

class Item (val name: String,val category: String, val mapGEO: String, val descrpition: String)
{

    override fun toString(): String {
        return name
    }
}