package com.example.contactdetailsapplication.ui.fragments

import androidx.navigation.fragment.navArgs
import com.example.contactdetailsapplication.R
import com.example.contactdetailsapplication.databinding.FragmentContactDetailsBinding
import com.example.contactdetailsapplication.databinding.FragmentHomeBinding

class ContactDetailsFragment :
    BaseFragment<FragmentContactDetailsBinding>(R.layout.fragment_contact_details) {

    private val args: ContactDetailsFragmentArgs by navArgs()
    override fun onViewCreatedOneTime() {
        val contact = args.contact
        binding.contact = contact
    }

    override fun onViewCreatedEverytime() {
    }

}