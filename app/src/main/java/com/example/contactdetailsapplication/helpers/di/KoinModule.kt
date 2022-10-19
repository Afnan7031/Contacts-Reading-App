package com.example.contactdetailsapplication.helpers.di

import com.example.contactdetailsapplication.ui.fragments.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel() }
}