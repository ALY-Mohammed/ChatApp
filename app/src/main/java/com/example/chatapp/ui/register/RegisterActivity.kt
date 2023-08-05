package com.example.chatapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.example.chatapp.ui.Base.BaseActivity
import com.example.chatapp.ui.register.RegisterViewModel

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm= viewModel
        observing()

    }

    override fun getLayout(): Int {
     return R.layout.activity_register
    }

    override fun getModel(): RegisterViewModel {
      return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    fun observing(){
        viewModel.showProgressBarLifeData.observe(this,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if (t==true)
                {
                    binding.progressBar.isVisible=true
                }else{
                    binding.progressBar.isVisible=false
                }
            }

        })
        viewModel.showDialogLifeData.observe(this,object:Observer<String>{
            override fun onChanged(t: String?) {
                if (t=="isSuccessful") {
                    showDialog("${t}", "الحمد لله ", "OK", posButtonAction = object :DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                            startActivity(intent)
                        }

                    })
                }else{
                    showDialog("is not Successful", " ${t} ", negButtonTitle = "dony")

                }
                binding.progressBar.isVisible=false
            }


        })

    }



}