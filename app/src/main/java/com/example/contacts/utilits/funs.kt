package com.example.contacts.utilits

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadPhoto(uri: String) {
    Picasso.get()
        .load(uri)
        .centerCrop()
        .fit()
        .into(this)
}