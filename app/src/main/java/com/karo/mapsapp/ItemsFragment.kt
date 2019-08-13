package com.karo.mapsapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_items.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ItemsFragment : Fragment() {
    var categoryName:String = ""
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryName = arguments!!.getString("categoryName")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_items, container, false)
        var categories:Array<String> = arrayOf("kat1", "kat2", "kat3")

        val items:ArrayList<Item> = ArrayList()
        for (i in 1..100)
        {

            items.add(Item("$i"))
        }
        val itemsView:RecyclerView=view.findViewById(R.id.itemsView)
        itemsView.layoutManager = LinearLayoutManager(context)
       // itemsView.adapter = ItemsList?.let { ItemsAdapter(it) }


        var title = categoryName
        (activity as AppCompatActivity).supportActionBar?.title = title

//        var items:Array<String> = arrayOf("fail")

        var listI : MutableList<Item> = mutableListOf()
        ItemsList?.forEach{ a->
                    if(a.category!!.contains(categoryName))
                    {
                        listI.add(a)
                    }
            }
        var adapter=ItemsAdapter(listI){
            var name = listI[it].name
            var bundle = bundleOf("name" to name)
            navController!!.navigate(
                R.id.action_itemsFragment_to_presentationFragment,
                bundle
            )
        }
        itemsView.adapter = adapter

/*

        //var listViewAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_list_item_1,listaI )

        var listView: ListView = view.findViewById(R.id.itemsView)
        //listView.adapter = listViewAdapter


        listView.setOnItemClickListener{ _, _, position, _ ->
            var name = listView.getItemAtPosition(position).toString()
            var bundle = bundleOf("name" to name)
            navController!!.navigate(
                R.id.action_itemsFragment_to_presentationFragment,
                bundle
            )
        }*/


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


    }

}
