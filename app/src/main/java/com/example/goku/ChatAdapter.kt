package com.example.goku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val chatList: ArrayList<ChatModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Kode untuk membedakan tipe pesan
    private val VIEW_TYPE_ME = 1
    private val VIEW_TYPE_OTHER = 2

    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].isSender) VIEW_TYPE_ME else VIEW_TYPE_OTHER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ME) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_me, parent, false)
            MeViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_other, parent, false)
            OtherViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        if (holder is MeViewHolder) {
            holder.tvMsg.text = chat.message
            holder.tvTime.text = chat.time
        } else if (holder is OtherViewHolder) {
            holder.tvMsg.text = chat.message
            holder.tvTime.text = chat.time
        }
    }

    override fun getItemCount(): Int = chatList.size

    // ViewHolder Saya
    inner class MeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMsg: TextView = itemView.findViewById(R.id.tvMessageMe)
        val tvTime: TextView = itemView.findViewById(R.id.tvTimeMe)
    }

    // ViewHolder Orang Lain
    inner class OtherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMsg: TextView = itemView.findViewById(R.id.tvMessageOther)
        val tvTime: TextView = itemView.findViewById(R.id.tvTimeOther)
    }
}