package com.example.inventoryapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoryapp.databases.Itemsdata
import kotlinx.android.synthetic.main.fragment_list_item.view.*

class ListFragment: Fragment() {
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_item, container, false)
        //introduce adapter
        val adapter = ItemAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        itemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        itemsViewModel.getAllItem.observe(viewLifecycleOwner, Observer { itemsData -> adapter.setData(itemsData) })


        view.floating_action.setOnClickListener {
            findNavController().navigate(R.id.action_list_itemFragment_to_addFragment)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenu)
            deleteAllItems()

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItems() {
            val builder= AlertDialog.Builder(requireContext())
                .setTitle("Delete every Item ")
                .setMessage("Are you sure you want to delete every Item ")
                .setPositiveButton("Yes "){_,_ ->
                    itemsViewModel.deleteAllItems()
                    Toast.makeText(requireContext(),"Successfully Deleted", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No"){_,_ ->}
            builder.create().show()
        }
    }








