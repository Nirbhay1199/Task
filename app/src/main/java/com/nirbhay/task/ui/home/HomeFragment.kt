package com.nirbhay.task.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nirbhay.task.MainActivity
import com.nirbhay.task.R
import com.nirbhay.task.database.TheProject
import com.nirbhay.task.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), RVAdapter {

    private lateinit var itemAdapter: RecyclerViewAdapter
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        itemAdapter = RecyclerViewAdapter(requireContext(),this)
        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        var count = 0
        homeViewModel.allItems.observe(viewLifecycleOwner){ list->
            list?.let {
                count = list.size
                itemAdapter.updateList(it)
            }
        }
        Log.e("tag",randomItem())


        Log.e("tag", count.toString())

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val addItem = TheProject(count + 1,randomItem())
            homeViewModel.insertItem(addItem)
        }
    }

    override fun onItemClicked(item: TheProject) {
        homeViewModel.deleteItem(item)
    }

    override fun onButtonClicked(item: TheProject) {
        val any = TheProject(item.code,item.anything)
        homeViewModel.insertItem(any)
    }

    private fun randomItem(): String = List(1){
        val list = listOf("Cloth","Brush","Television","Laptop","Box","Smartphone","Mouse","KeyBoard")
        list.random()
    }.joinToString("")

}