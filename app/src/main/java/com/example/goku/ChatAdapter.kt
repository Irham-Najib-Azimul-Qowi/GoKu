package com.example.goku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter untuk RecyclerView Chat.
 * Menangani tampilan pesan masuk (dari driver/admin) dan pesan keluar (dari user).
 */
class ChatAdapter(private val chatList: ArrayList<ChatModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Konstanta untuk menentukan tipe view
    private val VIEW_TYPE_ME = 1    // Pesan Saya (Kanan)
    private val VIEW_TYPE_OTHER = 2 // Pesan Orang Lain (Kiri)

    /**
     * Menentukan tipe view berdasarkan pengirim pesan.
     * Jika isSender = true -> VIEW_TYPE_ME
     * Jika isSender = false -> VIEW_TYPE_OTHER
     */
    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].isSender) VIEW_TYPE_ME else VIEW_TYPE_OTHER
    }

    /**
     * Membuat ViewHolder berdasarkan tipe view yang ditentukan di atas.
     * Menggunakan layout item_chat_me.xml atau item_chat_other.xml.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ME) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_me, parent, false)
            MeViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_other, parent, false)
            OtherViewHolder(view)
        }
    }

    /**
     * Mengikat data pesan ke ViewHolder yang sesuai.
     * Menampilkan teks pesan dan waktu.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        if (holder is MeViewHolder) {
            // Bind data untuk pesan sendiri
            holder.tvMsg.text = chat.message
            holder.tvTime.text = chat.time
        } else if (holder is OtherViewHolder) {
            // Bind data untuk pesan orang lain
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