package com.karo.mapsapp


import android.app.ActionBar
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
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
class CategoriesFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_categories, container, false)

        var title="Kategorie"
        (activity as AppCompatActivity).supportActionBar?.title = title


        var categories:Array<String> = arrayOf("kat1", "kat2", "kat3")
        var listViewAdapter:ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_list_item_1,categories )
        var listView: ListView = view.findViewById(R.id.categoriesView)
        listView.adapter = listViewAdapter

        listView.setOnItemClickListener{ _, _, position, _ ->
            var id = listView.getItemIdAtPosition(position)
            var bundle = bundleOf("categoryID" to id)
            navController!!.navigate(
                R.id.action_categoriesFragment_to_itemsFragment,
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
