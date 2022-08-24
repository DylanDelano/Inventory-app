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
import androidx.navigation.fragment.navArgs
import com.example.inventoryapp.databases.Itemsdata
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
    private lateinit var itemsViewModel: ItemsViewModel
    private val args by navArgs<EditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit, container, false)
        itemsViewModel= ViewModelProvider(this).get(ItemsViewModel::class.java)
        view.update_name_item.setText(args.editList.Item)
        view.update_price_amount.setText(args.editList.Price.toString())
        view.update_item.setText(args.editList.Quantity.toString())

        view.cancel_button.setOnClickListener {
            deleteUpdate()
        }


        view.update_items.setOnClickListener {
            editItems()
        }
        return view
    }

    private fun deleteUpdate() {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete ${args.editList.Item}")
            .setMessage("Are you sure you want to delete ${args.editList.Item}")
            .setPositiveButton("Yes") { _, _ ->
                itemsViewModel.deleteItems(args.editList)
                Toast.makeText(requireContext(), "Item Successfully deleted", Toast.LENGTH_LONG)
                    .show()
                findNavController().navigate(R.id.action_editFragment_to_list_itemFragment)
            }
            .setNegativeButton("No"){ _,_ ->}
        builder.create().show()
            }



    private fun editItems() {
        val Items = update_name_item.text.toString()
        val Price = update_price_amount.text
        val Quantity = update_item.text

        if (inputCheck(Items,Price,Quantity)){
            val update = Itemsdata(args.editList.id,Items,Integer.parseInt(Price.toString()),Integer.parseInt(Quantity.toString()))
            itemsViewModel.createItem(update)
            Toast.makeText(requireContext(),"Successfully Updated",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editFragment_to_list_itemFragment)
        }
        }


    private fun inputCheck(Item:String, Price:Editable?,Quantity:Editable?):Boolean{
        return!TextUtils.isEmpty(Item)|| (Price?.isEmpty() ?: Quantity?.isEmpty())!!
    }


}