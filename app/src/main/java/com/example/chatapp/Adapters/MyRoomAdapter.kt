package com.example.chatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.chatapp.ui.Room
import com.example.chatapp.R
import com.example.chatapp.databinding.ItemLeftMessageViewBinding
import com.example.chatapp.databinding.ItemRightMessageViewBinding
import com.example.chatapp.databinding.ItemRoomBinding
import com.example.chatapp.ui.Message
import com.example.chatapp.ui.MyUser


class MyRoomAdapter(var itemRoom:List<Room?>?):Adapter<MyRoomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<ItemRoomBinding>(LayoutInflater.from(parent.context),
            R.layout.item_room,parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return itemRoom!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = itemRoom!!.get(position)
        holder.bind(room)
        holder.binding.room=room

//        holder.binding.categoryIcon.setOnClickListener {
//            onRoomClicked?.onClicked()
//        }

        holder.binding.itemReference.setOnClickListener {
            onRoomClicked?.onClicked(room,position)
        }

    }


    class ViewHolder(val binding : ItemRoomBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(room: Room?) {
            binding.room = room
        }

    }

    fun onChange(rooms : List<Room?>?){
        itemRoom=rooms
        notifyDataSetChanged()
    }

    var onRoomClicked:OnRoomClick?=null

    interface OnRoomClick {
        fun onClicked(room: Room?,position: Int)
    }



}