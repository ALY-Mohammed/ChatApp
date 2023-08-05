package com.example.chatapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.ui.Base.BaseActivity
import com.example.chatapp.ui.Home.HomeChating
import com.example.chatapp.ui.MyUser
import com.example.chatapp.ui.login.LoginViewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        navigate()
        observing()
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun getModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    fun navigate() {
        binding.createAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun observing(){
        viewModel.showProgressBarLifeData.observe(this,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                binding.progressBar.isVisible = t==true
            }

        })
        viewModel.showDialogLifeData.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                if (t=="Successful Login") {
                    showDialog(
                        "${t}",
                        "الحمد لله تم بنجاح ",
                        "OK",
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                               val intent =Intent(this@LoginActivity,HomeChating::class.java)
                                startActivity(intent)
                            }

                        }
                    )
                }else{
                    showDialog(
                        "Not Successful Login",
                        "${t}",
                        "deny",
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                p0!!.dismiss()
                            }

                        }
                    )

                }
                binding.progressBar.isVisible=false
            }

        }

        )


    }




}