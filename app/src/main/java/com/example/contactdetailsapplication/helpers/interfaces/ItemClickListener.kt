package com.example.contactdetailsapplication.helpers.interfaces

import com.example.contactdetailsapplication.helpers.models.Contact

interface ItemClickListener {
    fun onItemClick(contact: Contact)
}