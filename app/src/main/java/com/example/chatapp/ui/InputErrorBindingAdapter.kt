package com.example.chatapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.chatapp.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("AddErrorInTextInput")
fun addErrorInTextInput(textInputLayout:TextInputLayout,error:String?){
    textInputLayout.error=error
}

@BindingAdapter("AddRoomCategoryImage")
fun addRoomCategoryImage(imageView: ImageView,roomCategory: String){
    if(roomCategory=="Sport") {
        imageView.setImageResource(R.drawable.sports)
    }else if(roomCategory=="Movies"){
        imageView.setImageResource(R.drawable.movies)
    }else{
        imageView.setImageResource(R.drawable.music)
    }


}