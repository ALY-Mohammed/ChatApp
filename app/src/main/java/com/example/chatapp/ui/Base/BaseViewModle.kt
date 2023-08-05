package com.example.chatapp.ui.Base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {

    var showDialogLifeData=MutableLiveData<String>()
    var showProgressBarLifeData=MutableLiveData<Boolean>()


}