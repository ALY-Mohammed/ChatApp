package com.example.chatapp.ui.chating

import android.util.Log
import androidx.appcompat.view.menu.MenuWrapperICS
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.ui.Base.BaseViewModel
import com.example.chatapp.ui.Message
import com.example.chatapp.ui.Navigators.SendMessageNavigator
import com.example.chatapp.ui.Room
import com.google.android.play.core.integrity.e
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ChatActivityViewModel : BaseViewModel() {

    lateinit var room: Room
    var navigator: SendMessageNavigator? = null
    var messageLiveData: MutableLiveData<MutableList<Message>> = MutableLiveData()


    fun addMessageToFireStore(message: Message) {
        val emptyDoc = Firebase.firestore.collection("room").document(room.id).collection("Message")
            .document()
        message.id = emptyDoc.id
        emptyDoc.set(message).addOnSuccessListener {
            navigator?.navigator()
        }
            .addOnFailureListener {
                return@addOnFailureListener
            }

    }

    fun getMessageFromFireBase() {

        Firebase.firestore.collection("room")
            .document(room.id)
            .collection("Message")
            .orderBy("time").addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.documents.isNotEmpty()) {
                    val messagesContent: MutableList<Message> = mutableListOf()
                    snapshot.documents.forEach() {
                        messagesContent.add(it.toObject<Message>()!!)
                    }
                    messageLiveData.value = messagesContent
                } else {
                    Log.d("Message", "Current data: null")
                }
            }

    }
}