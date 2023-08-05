package com.example.chatapp.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chatapp.ui.Base.BaseViewModel
import com.example.chatapp.ui.MyUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel: BaseViewModel() {

    var firstName=""
    var lastName=""
    var email=""
    var passWord=""
    var auth:FirebaseAuth = Firebase.auth


    var firstNameError:String? = null
    var lastNameError:String? = null
    var emailError:String? = null
    var passWordError:String? = null


    fun createAccount(){


        if (!validation()) return
        showProgressBarLifeData.value=true
        auth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener {
            if (it.isSuccessful){
                addUserCollection(it.result.user!!.uid)
            }else{

                Log.e("is not Successful","${it.exception!!.message}")
                showDialogLifeData.value="${it.exception!!.cause}"
            }

        }


    }

    fun addUserCollection(userId :String){
        val emptyDoc = Firebase.firestore.collection("Users")
            .document(userId)
        val user = MyUser(id=userId,firstName = firstName,lastName=lastName,email=email)

        emptyDoc.set(user).addOnSuccessListener {
            showProgressBarLifeData.value=false
            showDialogLifeData.value="isSuccessful"
        }
            .addOnFailureListener {
                return@addOnFailureListener
            }
    }


    fun validation():Boolean{
        var validate =true

        if (firstName.isEmpty()){
            firstNameError="pleas Enter first Name"
            validate=false
        }else{
            firstNameError=null
        }

        if (lastName.isEmpty()){
            lastNameError="pleas Enter first lastName"
            validate=false
        }else{
            lastNameError=null
        }

        if (email.isEmpty()){
            emailError="pleas Enter first email"
            validate=false
        }else{
            emailError=null
        }

        if (passWord.isEmpty()){
            passWordError="pleas Enter first PassWord"
            validate=false
        }else{
            passWordError=null
        }

        return validate

    }


}