package com.example.contactdetailsapplication.helpers.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.contactdetailsapplication.databinding.ItemContactBinding
import com.example.contactdetailsapplication.helpers.interfaces.ItemClickListener
import com.example.contactdetailsapplication.helpers.models.Contact


class AdapterContacts(
    private val contactList: ArrayList<Contact>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<AdapterContacts.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class CustomViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.contact = contact
            binding.clickListener = listener
        }
    }
}