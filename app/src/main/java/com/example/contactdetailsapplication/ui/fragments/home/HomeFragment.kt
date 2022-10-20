package com.example.contactdetailsapplication.ui.fragments.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.contactdetailsapplication.R
import com.example.contactdetailsapplication.databinding.FragmentHomeBinding
import com.example.contactdetailsapplication.helpers.ContactsLoader
import com.example.contactdetailsapplication.helpers.adapters.recyclerview.AdapterContacts
import com.example.contactdetailsapplication.helpers.interfaces.ItemClickListener
import com.example.contactdetailsapplication.helpers.models.Contact
import com.example.contactdetailsapplication.ui.fragments.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    HomeViewModel.ContactListener, ItemClickListener, BaseFragment.PermissionListener {

    private var adapter: AdapterContacts? = null
    override fun onViewCreatedOneTime() {
        permissionWork(this)
    }

    override fun onViewCreatedEverytime() {
        binding.progressBar.visibility = View.GONE
        binding.loadingText.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter = AdapterContacts(ContactsLoader.contactList, this)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        diComponent.contactViewModel.getContactList(mActivity, this)
    }

    override fun onContactsLoaded() {
        binding.progressBar.visibility = View.GONE
        binding.loadingText.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter = AdapterContacts(ContactsLoader.contactList, this)
        binding.recyclerView.adapter = adapter
    }

    override fun onItemClick(contact: Contact) {
        if (!isAdded)
            return
        val action = HomeFragmentDirections.actionHomeFragmentToContactDetailsFragment(contact)
        findNavController().navigate(action)
    }

    override fun isPermissionGranted(isGranted: Boolean) {
        if (isGranted) {
            initViewModel()
            return
        }
        binding.progressBar.visibility = View.GONE
        binding.loadingText.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        permissionWork(this)
        Toast.makeText(mContext, "You don't have Contacts permission", Toast.LENGTH_SHORT).show()
    }


}