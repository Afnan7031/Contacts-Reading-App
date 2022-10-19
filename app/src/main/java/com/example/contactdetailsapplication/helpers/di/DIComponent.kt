package com.example.contactdetailsapplication.helpers.di

import com.example.contactdetailsapplication.ui.fragments.home.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIComponent : KoinComponent {
    // ViewModel
    val contactViewModel by inject<HomeViewModel>()
}