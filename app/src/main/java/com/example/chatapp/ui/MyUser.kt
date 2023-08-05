package com.example.chatapp.ui

data class MyUser(
    var id :String = "",
    var firstName :String = "",
    var lastName :String = "",
    var email :String = ""
)
{
    companion object{
        var curntuser = MyUser()
    }
}

