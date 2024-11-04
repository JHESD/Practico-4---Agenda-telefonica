package com.example.agendatelefonica.API

import com.example.agendatelefonica.Models.EmailUser
import com.example.agendatelefonica.Models.PersonUser
import com.example.agendatelefonica.Models.PersonasLista
import com.example.agendatelefonica.Models.PhoneUser
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface JSON_PHS {
    // Obtener lista de contactos
    @GET("personas")
    fun getContactList(
    ):Call<PersonasLista>

    // Buscar contactos
    @GET("search")
    fun searchContacts(
        @Query("q") query: String
    ): Call<PersonasLista>

    // Agregar contacto
    @POST("personas")
    fun addContact(
        @Body contact: PersonUser
    ): Call<PersonUser>

    // Actualizar contacto
    @PUT("personas/{id}")
    fun updateContact(
        @Path("id") id: Int,
        @Body contact: PersonUser
    ): Call<PersonUser>

    // Eliminar contacto
    @DELETE("personas/{id}")
    fun deleteContact(
        @Path("id") id: Int
    ): Call<Void>

    // Obtener contacto por ID
    @GET("personas/{id}")
    fun getContactById(
        @Path("id") id: Int
    ): Call<PersonUser>

    // Obtener lista de teléfonos
    @GET("phones")
    fun getPhoneList(
    ): Call<List<PhoneUser>>

    // Agregar teléfono
    @POST("phones")
    fun addPhone(
        @Body phone: PhoneUser
    ): Call<PhoneUser>

    // Subir foto de perfil
    @Multipart
    @POST("personas/{id}/profile-picture")
    fun uploadProfilePicture(
        @Path("id") id: Int,
        @Part file: MultipartBody.Part
    ): Call<Void>

    // Agregar correo
    @POST("emails")
    fun addEmail(
        @Body email: EmailUser
    ): Call<EmailUser>

    // Obtener lista de correos
    @GET("emails")
    fun getEmailList(
    ): Call<List<EmailUser>>
}