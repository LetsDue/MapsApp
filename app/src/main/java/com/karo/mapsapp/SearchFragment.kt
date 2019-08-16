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


class SearchFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val title=getString(R.string.Szukaj_string)
        (activity as AppCompatActivity).supportActionBar?.title = title
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val categorySearchView: RecyclerView = view.findViewById(R.id.CategorySearchView)
        categorySearchView.layoutManager = LinearLayoutManager(context)
        val adapter=CategoriesAdapter(CategoryList!!){
            val categoryName = CategoryList!![it]
            val bundle = bundleOf("categoryName" to categoryName)
            navController.navigate(
                R.id.action_searchFragment_to_itemsFragment,
                bundle
            )
        }
        categorySearchView.adapter = adapter

        val itemSearchView: RecyclerView = view.findViewById(R.id.ItemSearchView)
        itemSearchView.layoutManager = LinearLayoutManager(context)
        val adapterI=ItemsAdapter(ItemsList!!){
            val name = ItemsList!![it].name
            val bundle = bundleOf("name" to name)
            navController.navigate(
                R.id.action_searchFragment_to_presentationFragment,
                bundle
            )
        }
        itemSearchView.adapter = adapterI


        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                val newCategoryList:MutableList<String> = mutableListOf()
                CategoryList!!.forEach { cat->
                    if(cat.toLowerCase().contains(searchView.query.toString().toLowerCase()))
                    {
                        newCategoryList.add(cat)
                    }
                }
                adapter.filterList(newCategoryList)

                val newItemsList:MutableList<Item> = mutableListOf()
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
