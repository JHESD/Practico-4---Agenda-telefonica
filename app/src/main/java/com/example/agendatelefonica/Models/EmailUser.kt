package com.example.agendatelefonica.Models

typealias EmailList = ArrayList<EmailUser>

data class EmailUser(
    val id: Int,
    val email: String,
    val persona_id: Int,
    val label: String
)
