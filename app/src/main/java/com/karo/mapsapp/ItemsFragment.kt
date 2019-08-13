package com.karo.mapsapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val itemsView:RecyclerView=view.findViewById(R.id.itemsView)
        itemsView.layoutManager = LinearLayoutManager(context)

        var title = categoryName
        (activity as AppCompatActivity).supportActionBar?.title = title
        var categoryFoundList : MutableList<Item> = mutableListOf()

        ItemsList?.forEach{ a->
                    if(a.category!!.contains(categoryName))
                    {
                        categoryFoundList.add(a)
                    }
            }
        var adapter=ItemsAdapter(categoryFoundList){
            var name = categoryFoundList[it].name
            var bundle = bundleOf("name" to name)
            navController!!.navigate(
                R.id.action_itemsFragment_to_presentationFragment,
                bundle
            )
        }
        itemsView.adapter = adapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


    }

}
