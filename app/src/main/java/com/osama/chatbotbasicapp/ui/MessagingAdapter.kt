package com.osama.chatbotbasicapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.osama.chatbotbasicapp.R
import com.osama.chatbotbasicapp.data.Message
import com.osama.chatbotbasicapp.utils.Constants

class MessagingAdapter(messages: MutableList<Message>) :
    RecyclerView.Adapter<MessagingAdapter.MessageCustomViewHolder>() {

    private val messagesList: MutableList<Message> = messages


    inner class MessageCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageCustomViewHolder {
        return MessageCustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageCustomViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        val senderTextView = holder.itemView.findViewById<AppCompatTextView>(R.id.sender_text)
        val botTextView = holder.itemView.findViewById<AppCompatTextView>(R.id.bot_text)
        when (currentMessage.id) {
            Constants.SENDER_ID -> {
                senderTextView.apply {
                    visibility = View.VISIBLE
                    botTextView.visibility = View.GONE
                    text = currentMessage.message
                }
            }

            Constants.RECEIVER_ID -> {
                botTextView.apply {
                    visibility = View.VISIBLE
                    senderTextView.visibility = View.GONE
                    text = currentMessage.message
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    public fun insertNewMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}