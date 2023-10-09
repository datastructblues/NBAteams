package com.example.nbateams.utils

import android.view.View
import android.widget.ImageView
import androidx.compose.ui.text.toLowerCase
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.Locale

@BindingAdapter("android:setSrc")
fun setImageResource(imageView: ImageView, resource: String?) {
    //load url with glide
    println("resource $resource")
    resource?.let {
        Glide.with(imageView.context).load(it).into(imageView)
    }
}

@BindingAdapter("android:setPrimaryColorBg")
fun setBackgroundColor(view:View, color: String?){
    color?.let {
        //parse color string to int
        view.setBackgroundColor(android.graphics.Color.parseColor(it.lowercase()))
    }
}

@BindingAdapter("android:setListText")
fun setText(view:View, list: List<String>?){
    list?.let {
        var text = ""
        for (i in list.indices) {
            text += list[i]
            if(i != list.size-1){
                text += ", "
            }
        }
        (view as ImageView).setImageResource(android.R.color.transparent)
        (view as ImageView).setImageResource(android.R.color.transparent)
    }
}