package com.karo.mapsapp


import android.icu.util.UniversalTimeScale.toLong
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ItemsFragment : Fragment() {
    var categoryID:Long = 0
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryID = arguments!!.getLong("categoryID")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_items, container, false)
        var categories:Array<String> = arrayOf("kat1", "kat2", "kat3")
        var title = categories[categoryID.toInt()]
        (activity as AppCompatActivity).supportActionBar?.title = title
        var items1:Array<String> = arrayOf("zoo", "i2", "i3", "i4", "i5", "i6", "i7", "i8", "i9")
        var items2:Array<String> = arrayOf("ratusz", "b12", "b13", "b14", "b15", "b16", "b17", "b18", "b1i9")
        var items3:Array<String> = arrayOf("c1", "c2", "lotnisko", "c1", "v")
        var items:Array<String> = arrayOf("fail")
        when(categoryID)
        {
            0.toLong() -> items = items1
            1.toLong() -> items = items2
            2.toLong() -> items = items3
        }

        var listViewAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_list_item_1,items )

        var listView: ListView = view.findViewById(R.id.itemsView)
        listView.adapter = listViewAdapter


        listView.setOnItemClickListener{ _, _, position, _ ->
            var name = listView.getItemAtPosition(position).toString()
            var bundle = bundleOf("name" to name)
            //var i:Item = Item("name","cat","geo","de")
            //var bundle = bundleOf("name" to i)
            navController!!.navigate(
                R.id.action_itemsFragment_to_presentationFragment,
                bundle
            )
        }


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


    }

}
