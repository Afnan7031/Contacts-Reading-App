package com.example.contactdetailsapplication.helpers.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var contactName: String,
    var contactPhoto: String,
    var contactPhone: String,
    var contactFax: String,
    var contactEmail: String,
    var contactMobile: String,
    var contactBirthday: String
) : Parcelable