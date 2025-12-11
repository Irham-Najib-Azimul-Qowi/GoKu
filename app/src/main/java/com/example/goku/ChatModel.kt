package com.example.goku

data class ChatModel(
    val message: String,
    val time: String,
    val isSender: Boolean // true = pesan saya (kanan), false = pesan orang (kiri)
)