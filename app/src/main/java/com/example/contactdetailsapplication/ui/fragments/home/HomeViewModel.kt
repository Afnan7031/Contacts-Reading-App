package com.example.contactdetailsapplication.ui.fragments.home

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.contactdetailsapplication.helpers.ContactsLoader

class HomeViewModel : ViewModel() {

    fun getContactList(activity: Activity, contactListener: ContactListener) {
        ContactsLoader.getContactList(activity) {
            contactListener.onContactsLoaded()
        }
    }

    interface ContactListener {
        fun onContactsLoaded()
    }
}