package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity() {

    // Siapkan wadah untuk list chat dan adapter
    private lateinit var chatAdapter: ChatAdapter
    private val chatList = ArrayList<ChatModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 1. Inisialisasi View
        val btnBack = findViewById<ImageView>(R.id.btnBackChat)
        val btnCall = findViewById<ImageView>(R.id.btnCallAction) // Tombol Telpon
        val btnSend = findViewById<ImageView>(R.id.btnSend)
        val etMessage = findViewById<EditText>(R.id.etMessageInput)
        val rvChat = findViewById<RecyclerView>(R.id.rvChatHistory)

        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        // 2. Setup Data Chat Awal (Dummy)
        chatList.add(ChatModel("Halo mas, sesuai titik ya mas?", "12.00", false)) // Pesan orang
        chatList.add(ChatModel("Betul Mas", "12.00", true)) // Pesan saya

        // 3. Setup RecyclerView
        chatAdapter = ChatAdapter(chatList)
        rvChat.layoutManager = LinearLayoutManager(this)
        rvChat.adapter = chatAdapter

        // 4. LOGIKA TOMBOL KIRIM
        btnSend.setOnClickListener {
            val messageText = etMessage.text.toString()

            if (messageText.isNotEmpty()) {
                // Ambil jam sekarang
                val currentTime = SimpleDateFormat("HH.mm", Locale.getDefault()).format(Date())

                // Tambahkan pesan baru ke list sebagai 'Sender' (true)
                chatList.add(ChatModel(messageText, currentTime, true))

                // Beritahu adapter ada data baru di posisi paling bawah
                chatAdapter.notifyItemInserted(chatList.size - 1)

                // Scroll otomatis ke bawah
                rvChat.smoothScrollToPosition(chatList.size - 1)

                // Kosongkan kolom ketik
                etMessage.setText("")
            }
        }

        // 5. LOGIKA TOMBOL BACK
        btnBack.setOnClickListener { finish() }

        // 6. LOGIKA PINDAH KE TELEPON
        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
    }
}