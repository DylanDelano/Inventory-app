package com.example.inventoryapp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inventoryapp.databases.Itemsdata
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var itemsViewModel: ItemsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        itemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        view.save_items.setOnClickListener {
            addItems()
        }
        return view
    }




    fun addItems() {
         val item = edit_name_item.text.toString()
         val quantity = edit_item.text
         val price = edit_price_amount.text

         if (inputCheck(item, quantity, price)) {
             val add = Itemsdata(0,item,Integer.parseInt(price.toString()),Integer.parseInt(quantity.toString()))
             itemsViewModel.newItem(add)
             Toast.makeText(requireContext(), "Successfully added ", Toast.LENGTH_LONG).show()
             findNavController().navigate(R.id.action_addFragment_to_list_itemFragment)
         }


         else{
             Toast.makeText(requireContext(),"Please fill the relevant fields ",Toast.LENGTH_LONG).show()
         }

     }


    private fun inputCheck(item:String, quantity: Editable?, price: Editable?):Boolean{
        return !TextUtils.isEmpty(item) || (quantity?.isEmpty() ?:  price?.isEmpty())!!
    }
     }
