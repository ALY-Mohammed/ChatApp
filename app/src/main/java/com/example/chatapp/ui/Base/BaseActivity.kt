package com.example.chatapp.ui.Base

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.chatapp.R

open abstract class BaseActivity<VM:ViewModel,B:ViewDataBinding>:AppCompatActivity() {
    lateinit var binding:B
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, getLayout())
        viewModel=getModel()

    }

    abstract fun getLayout():Int
    abstract fun getModel():VM


    fun showDialog(
         title:String ,
         message:String?=null ,
         posButtonTitle:String?=null,
         posButtonAction:DialogInterface.OnClickListener?=null,
         negButtonTitle:String?=null,
         negButtonAction: DialogInterface.OnClickListener?=null
    ) {
        var builder = AlertDialog.Builder(this)

        val defaultAction= object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0!!.dismiss()
            }
        }
        builder.setTitle(title)
        builder.setMessage(message)
        if(posButtonTitle!=null) {
            builder.setPositiveButton(posButtonTitle, posButtonAction ?: defaultAction)
        }
        if(negButtonTitle!=null) {
            builder.setNegativeButton(negButtonTitle, negButtonAction ?: defaultAction)
        }
        builder.show()

    }



}