package com.karo.mapsapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.fragment_search.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var title="Szukaj"
        (activity as AppCompatActivity).supportActionBar?.title = title
        var view = inflater.inflate(R.layout.fragment_search, container, false)

        var categorySearchView: RecyclerView = view.findViewById(R.id.CategorySearchView)
        categorySearchView.layoutManager = LinearLayoutManager(context)
        var adapter=CategoriesAdapter(CategoryList!!){
            var categoryName = CategoryList!![it]
            var bundle = bundleOf("categoryName" to categoryName)
            navController!!.navigate(
                R.id.action_searchFragment_to_itemsFragment,
                bundle
            )
        }
        categorySearchView.adapter = adapter

        var itemSearchView: RecyclerView = view.findViewById(R.id.ItemSearchView)
        itemSearchView.layoutManager = LinearLayoutManager(context)
        var adapterI=ItemsAdapter(ItemsList!!){
            var name = ItemsList!![it].name
            var bundle = bundleOf("name" to name)
            navController!!.navigate(
                R.id.action_searchFragment_to_presentationFragment,
                bundle
            )
        }
        itemSearchView.adapter = adapterI


        var searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                var newCategoryList:MutableList<String> = mutableListOf()
                CategoryList!!.forEach { cat->
                    if(cat.toLowerCase().contains(searchView.query.toString().toLowerCase()))
                    {
                        newCategoryList.add(cat)
                    }
                }
                adapter.filterList(newCategoryList)

                var newItemsList:MutableList<Item> = mutableListOf()
                ItemsList!!.forEach { item->
                    if(item.name!!.toLowerCase().contains(searchView.query.toString().toLowerCase()))
                    {
                        newItemsList.add(item)
                    }
                }
                adapterI.filterList(newItemsList)
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}
