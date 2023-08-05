package com.example.chatapp.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.Adapters.MyRoomAdapter
import com.example.chatapp.ui.Room
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentMyRoomsBinding
import com.example.chatapp.ui.Base.BaseFragment
import com.example.chatapp.ui.chating.ChatActivity

class MyRoomsFragment : BaseFragment< MyRoomFragmentViewModel, FragmentMyRoomsBinding >() {

     var adapter = MyRoomAdapter(listOf())


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.MyRoomRecyclerView.adapter=adapter
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        viewModelFragment.getRoomFromFireBase()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_my_rooms
    }

    override fun getViewModel(): MyRoomFragmentViewModel {
        return ViewModelProvider(this).get(MyRoomFragmentViewModel::class.java)
    }

     fun initListeners(){
         viewModelFragment.adapterRoomLiveData.observe(viewLifecycleOwner,object : Observer<List<Room?>>{
        override fun onChanged(t: List<Room?>?) {
            adapter.onChange(t)

        }

    })
         adapter.onRoomClicked= object:MyRoomAdapter.OnRoomClick{
             override fun onClicked(room: Room?, position: Int) {
                 onRoomFragmentClick?.onClicked(room,position)
             }


         }


    }

    var onRoomFragmentClick:OnRoomFragmentClick?=null

    interface OnRoomFragmentClick {
        fun onClicked(room:Room?,position :Int)
    }




}