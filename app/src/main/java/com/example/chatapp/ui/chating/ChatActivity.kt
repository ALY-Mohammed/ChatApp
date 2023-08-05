package com.example.chatapp.ui.chating

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Adapters.MessageAdapter
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.ui.Base.BaseActivity
import com.example.chatapp.ui.Message
import com.example.chatapp.ui.MyUser
import com.example.chatapp.ui.Navigators.SendMessageNavigator
import com.example.chatapp.ui.Room
import java.util.Calendar

class ChatActivity :BaseActivity<ChatActivityViewModel,ActivityChatBinding>(),SendMessageNavigator {
     var adapter: MessageAdapter = MessageAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator=this
        viewModel.room= intent.getSerializableExtra("room") as  Room
        initListener()
        var layoutManager = LinearLayoutManager(this);
//        layoutManager.reverseLayout = true;
  //      layoutManager.stackFromEnd = true;
        binding.messageRecyclerView.layoutManager =layoutManager
        binding.messageRecyclerView.adapter =adapter


//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
        viewModel.getMessageFromFireBase()
    }

    override fun getLayout(): Int {
        return R.layout.activity_chat
    }

    override fun getModel(): ChatActivityViewModel {
        return ViewModelProvider(this).get(ChatActivityViewModel::class.java)
    }

    private fun initListener() {
        binding.SendButton.setOnClickListener {
            val message = Message(id = "",
                messageContent = binding.messageText.editText!!.text.toString(),
                senderId = MyUser.curntuser.id ,
                time = Calendar.getInstance().time.time,
                senderName = MyUser.curntuser.firstName + " " + MyUser.curntuser.lastName
            )
            viewModel.addMessageToFireStore(message)
        }

        viewModel.messageLiveData.observe(this,object:Observer<MutableList<Message>>{
            override fun onChanged(t: MutableList<Message>?) {
                t!!.toList()
                adapter.onChange(t)
            }

        })
    }

    override fun navigator() {
     binding.messageText.editText!!.text=null
    }


//    fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
//        val message: Message = messageList.get(position)
//        if (position > lastVisibleItemPosition) {
//            holder.itemView.setVisibility(View.VISIBLE)
//            holder.messageText.setText(message.getText())
//            holder.senderName.setText(message.getSenderName())
//            holder.timestamp.setText(message.getTimestamp())
//        } else {
//            holder.itemView.setVisibility(View.GONE)
//        }
//    }

//    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//
//            // Get the last visible item position
//            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
//
//            // Update the last visible item position
//            if (lastVisiblePosition > lastVisibleItemPosition) {
//                lastVisibleItemPosition = lastVisiblePosition;
//
//                // Notify the adapter to show the newly visible items
//                adapter.notifyItemRangeChanged(lastVisibleItemPosition + 1, messageList.size() - 1);
//            }
//        }
//    });

}