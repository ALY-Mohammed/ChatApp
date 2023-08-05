package com.example.chatapp.ui.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

abstract class BaseFragment<VM:ViewModel,B:ViewDataBinding>:Fragment() {

    lateinit var binding: B
    lateinit var viewModelFragment: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,getLayout(),container,false)
        viewModelFragment=getViewModel()
        return (binding.root)
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }


    abstract fun getLayout():Int

    abstract fun getViewModel():VM


}