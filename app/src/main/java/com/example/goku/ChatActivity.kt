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

/**
 * ChatActivity
 * Halaman pesan instan antara user dan driver.
 * Menggunakan RecyclerView untuk menampilkan riwayat chat.
 */
class ChatActivity : AppCompatActivity() {

    // Adapter dan List untuk RecyclerView Chat
    private lateinit var chatAdapter: ChatAdapter
    private val chatList = ArrayList<ChatModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 1. Inisialisasi View
        val btnBack = findViewById<ImageView>(R.id.btnBackChat)
        val btnCall = findViewById<ImageView>(R.id.btnCallAction) // Tombol Aksi Telepon di Header
        val btnSend = findViewById<ImageView>(R.id.btnSend)       // Tombol Kirim Pesan
        val etMessage = findViewById<EditText>(R.id.etMessageInput)
        val rvChat = findViewById<RecyclerView>(R.id.rvChatHistory)

        // Listener Tombol Call
        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        // 2. Setup Data Chat Awal (Dummy Data)
        chatList.add(ChatModel("Halo mas, sesuai titik ya mas?", "12.00", false)) // Pesan dari Driver
        chatList.add(ChatModel("Betul Mas", "12.00", true)) // Pesan dari User (Saya)

        // 3. Setup RecyclerView dan Adapter
        chatAdapter = ChatAdapter(chatList)
        rvChat.layoutManager = LinearLayoutManager(this)
        rvChat.adapter = chatAdapter

        // 4. LOGIKA TOMBOL KIRIM PESAN
        btnSend.setOnClickListener {
            val messageText = etMessage.text.toString()

            if (messageText.isNotEmpty()) {
                // Ambil jam sekarang
                val currentTime = SimpleDateFormat("HH.mm", Locale.getDefault()).format(Date())

                // Tambahkan pesan baru ke list sebagai 'Sender' (isSender = true)
                chatList.add(ChatModel(messageText, currentTime, true))

                // Beritahu adapter ada data baru di posisi paling bawah
                chatAdapter.notifyItemInserted(chatList.size - 1)

                // Scroll otomatis ke item terbaru (paling bawah)
                rvChat.smoothScrollToPosition(chatList.size - 1)

                // Kosongkan kolom input teks
                etMessage.setText("")
            }
        }

        // 5. LOGIKA TOMBOL BACK -> Tutup halaman chat
        btnBack.setOnClickListener { finish() }

        // 6. LOGIKA PINDAH KE TELEPON (Duplikat listener di atas, bisa dihapus salah satu jika redundant)
        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
    }
}