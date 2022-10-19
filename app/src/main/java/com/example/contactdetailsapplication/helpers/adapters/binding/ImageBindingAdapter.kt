package com.example.contactdetailsapplication.helpers.adapters.binding

import android.net.Uri
import android.text.TextUtils
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.contactdetailsapplication.R

@BindingAdapter("setImageFromUri")
fun ImageFilterView.setImageFromUri(imageUri: String) {

        Glide
            .with(this)
            .load(imageUri)
            .placeholder(ContextCompat.getDrawable(this.context, R.drawable.profile_user))
            .into(this)
    }
