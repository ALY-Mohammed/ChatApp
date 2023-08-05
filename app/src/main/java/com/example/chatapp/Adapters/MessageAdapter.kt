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
import java.util.zip.Inflater


class MessageAdapter(var itemMessage: List<Message>) : Adapter<MessageAdapter.ViewHolder>() {

    lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == 1) {

            binding = DataBindingUtil.inflate<ItemRightMessageViewBinding>(
                LayoutInflater.from(parent.context),
                R.layout
                    .item_right_message_view, parent, false
            )

        } else if (viewType == 2) {
            binding = DataBindingUtil.inflate<ItemLeftMessageViewBinding>(
                LayoutInflater.from(parent.context),
                R.layout
                    .item_left_message_view, parent, false
            )
        }

        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val messageContent = itemMessage.get(position)

        if (messageContent.senderId == MyUser.curntuser.id) {
            return 1
        } else {
            return 2
        }
    }


    override fun getItemCount(): Int = itemMessage.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = itemMessage.get(position)
        holder.bind(message)
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (message:Message){
            if (binding is ItemRightMessageViewBinding){
                binding.message= message
            }else if (binding is ItemLeftMessageViewBinding){
                binding.message=message
            }

        }
    }

    fun onChange(message: List<Message>) {
        itemMessage = message
        notifyDataSetChanged()
    }
}



