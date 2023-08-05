package com.example.chatapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.BrowserFragmentBinding
import com.example.chatapp.databinding.FragmentMyRoomsBinding
import com.example.chatapp.ui.Base.BaseFragment
import com.example.chatapp.ui.Room

class BrowserFragment :BaseFragment<BrowserFragmentViewModel,BrowserFragmentBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }


    override fun getLayout(): Int {
      return R.layout.browser_fragment
    }

    override fun getViewModel(): BrowserFragmentViewModel {
        return  ViewModelProvider(this).get(BrowserFragmentViewModel::class.java)
    }


    fun initListeners(){


    }

}