package com.example.chatapp.ui.addRoom

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityAddNewRoomBinding
import com.example.chatapp.generated.callback.OnClickListener
import com.example.chatapp.ui.Base.BaseActivity
import com.example.chatapp.ui.Navigators.NavigateFromCreateRoom
import com.example.chatapp.ui.Room

class AddNewRoom : BaseActivity<AddNewRoomViewModel, ActivityAddNewRoomBinding>(),
    NavigateFromCreateRoom {

    var categorySpinner: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        initListener()

    }

    override fun getLayout(): Int {
        return R.layout.activity_add_new_room
    }

    override fun getModel(): AddNewRoomViewModel {
        return ViewModelProvider(this).get(AddNewRoomViewModel::class.java)
    }

    private fun initListener() {

        if (binding.RoomName.editText?.text != null && binding.RoomDescription.editText?.text != null) {
            binding.CreateRoom.setOnClickListener {

                val room = Room(
                    id = "",
                    title = binding.RoomName.editText!!.text.toString(),
                    description = binding.RoomDescription.editText!!.text.toString(),
                    roomCategory = categorySpinner
                )

                viewModel.addRoomInFirebase(room)


            }

            binding.Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                    val array: Array<String> = resources.getStringArray(R.array.categoryRoom)
                    categorySpinner = array[index]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

        }else{
            return
        }
    }



    override fun navigateToHome() {
        finish()
    }




}