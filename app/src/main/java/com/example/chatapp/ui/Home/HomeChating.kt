package com.example.chatapp.ui.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.chatapp.Adapters.MyRoomAdapter
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityHomeChatingBinding
import com.example.chatapp.ui.Base.BaseActivity
import com.example.chatapp.ui.Fragment.BrowserFragment
import com.example.chatapp.ui.Fragment.MyRoomsFragment
import com.example.chatapp.ui.Room
import com.example.chatapp.ui.addRoom.AddNewRoom
import com.example.chatapp.ui.chating.ChatActivity

class HomeChating : BaseActivity<HomeChatViewModel, ActivityHomeChatingBinding>() {

    private var myRoomFragment = MyRoomsFragment()
    var browserFragment = BrowserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
        pushFragment(myRoomFragment)

    }

    override fun getLayout(): Int {
        return R.layout.activity_home_chating
    }

    override fun getModel(): HomeChatViewModel {
        return ViewModelProvider(this).get(HomeChatViewModel::class.java)
    }

    private fun initListeners() {

        binding.Fab.setOnClickListener {
            Log.e("sssssssssss","sfffffff")
            val intent = Intent(this, AddNewRoom::class.java)
            startActivity(intent)
        }

        binding.MyRooms.setOnClickListener {
            binding.Browse.setTextColor(getColor(R.color.white))

            binding.MyRooms.setTextColor(getColor(R.color.black))
            pushFragment(myRoomFragment)
        }

        binding.Browse.setOnClickListener {
            binding.MyRooms.setTextColor(getColor(R.color.white))
            binding.Browse.setTextColor(getColor(R.color.black))
            pushFragment(browserFragment)
        }

        myRoomFragment.onRoomFragmentClick=object : MyRoomsFragment.OnRoomFragmentClick{
            override fun onClicked(room: Room?, position: Int) {
                val intent =Intent(this@HomeChating,ChatActivity::class.java)
                intent.putExtra("room",room)
                startActivity(intent)
            }


        }

    }


    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHome, fragment)
            .commit()
    }

}