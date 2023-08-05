package com.example.chatapp.ui

data class Message(
    var id :String = "",
    val messageContent:String ="",
    val senderId:String="",
    val time : Long=0,
    val senderName :String=""

)
