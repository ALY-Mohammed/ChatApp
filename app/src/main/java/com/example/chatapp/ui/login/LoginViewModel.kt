package com.example.chatapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chatapp.ui.Base.BaseViewModel
import com.example.chatapp.ui.MyUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel() {

    var email = ""
    var passWord = ""
    var loginEmailError: String? = null
    var loginPasswordError: String? = null

    var auth: FirebaseAuth = Firebase.auth


    fun login() {
        if (!validation()) return

        showProgressBarLifeData.value = true
        auth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener {
            if (it.isSuccessful) {
                getUserDataFromFireStore(it.result.user!!.uid)
                showProgressBarLifeData.value = false
                Log.e("isSuccessful", "isSuccessful")
                showDialogLifeData.value = "Successful Login"
            } else {

                Log.e("is not Successful", "${it.exception!!.message}")

                showDialogLifeData.value = "${it.exception!!.cause}"

            }
        }
    }



    private fun getUserDataFromFireStore(userId: String) {
        Firebase.firestore.collection("Users").document(userId)
            .get().addOnSuccessListener {
                val user = it.toObject<MyUser>() as MyUser
                MyUser.curntuser = user
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }


    }

        fun validation(): Boolean {
            var validate = true

            if (email.isEmpty()) {
                loginEmailError = "pleas Enter first email"
                validate = false
            } else {
                loginEmailError = null
            }

            if (passWord.isEmpty()) {
                loginPasswordError = "pleas Enter first PassWord"
                validate = false
            } else {
                loginPasswordError = null
            }

            return validate

        }


}