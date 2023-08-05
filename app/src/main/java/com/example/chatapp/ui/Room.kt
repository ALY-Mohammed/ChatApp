package com.example.chatapp.ui

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Room(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val roomCategory: String = ""
):Serializable



