package com.example.agendatelefonica.Models

typealias PersonasLista = ArrayList<PersonUser>

data class PersonUser(
    val id: Int,
    val name: String,
    val last_name: String,
    val company: String,
    val address: String,
    val city: String,
    val state: String,
    val profile_picture: String,
    val phones: List<PhoneUser>,
    val emails: List<EmailUser>
)