package com.example.chatapp.ui.Fragment

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.ui.Base.BaseViewModel
import com.example.chatapp.ui.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MyRoomFragmentViewModel: BaseViewModel(){


    var adapterRoomLiveData= MutableLiveData<List<Room>>()
    
    fun getRoomFromFireBase(){
        Firebase.firestore.collection("room")
            .get().addOnSuccessListener {
                var mutableList : MutableList<Room> = mutableListOf()
                it.documents.forEach {
                    mutableList.add(it.toObject<Room>()!!)
                }
                adapterRoomLiveData.value = mutableList
            }
    }
}