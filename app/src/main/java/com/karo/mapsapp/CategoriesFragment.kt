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


class CategoriesFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        val title="Kategorie"
        (activity as AppCompatActivity).supportActionBar?.title = title

        val categoriesView:RecyclerView  = view.findViewById(R.id.categoriesView)
        categoriesView.layoutManager = LinearLayoutManager(context)
        val adapter=CategoriesAdapter(CategoryList!!){
            val categoryName = CategoryList!![it]
            val bundle = bundleOf("categoryName" to categoryName)
            navController.navigate(
                R.id.action_categoriesFragment_to_itemsFragment,
                bundle
            )
        }
        categoriesView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


    }


}
