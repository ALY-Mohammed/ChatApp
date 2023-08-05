package com.example.chatapp.ui.addRoom

import android.util.Log
import android.widget.Toast
import com.example.chatapp.ui.Base.BaseViewModel
import com.example.chatapp.ui.Navigators.NavigateFromCreateRoom
import com.example.chatapp.ui.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNewRoomViewModel:BaseViewModel() {

    var navigator :NavigateFromCreateRoom?=null

    fun addRoomInFirebase(room: Room){

       val emptyDoc= Firebase.firestore.collection("room")
            .document()
           room.id = emptyDoc.id

        emptyDoc.set(room).addOnSuccessListener {
            Log.e("AAAAAAAAAAAAAAAAAAAAAa","$room")
            navigator!!.navigateToHome()
        }
    }

}