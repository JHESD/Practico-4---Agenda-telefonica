package com.example.agendatelefonica.Repositories.Model_Repositories

import com.example.agendatelefonica.API.JSON_PHS
import com.example.agendatelefonica.Models.EmailUser
import com.example.agendatelefonica.Repositories.RetrofitRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmailUsrRetro {
    private val service = RetrofitRepo.getRetrofitInstance()
        .create(JSON_PHS::class.java)

    // Obtener lista de emails
    fun getEmailList(onSuccess: (List<EmailUser>) -> Unit, onError: (Throwable) -> Unit) {
        service.getEmailList().enqueue(object : Callback<List<EmailUser>> {
            override fun onResponse(call: Call<List<EmailUser>>, response: Response<List<EmailUser>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<EmailUser>>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Agregar email
    fun addEmailUser(email: EmailUser, onSuccess: (EmailUser) -> Unit, onError: (Throwable) -> Unit) {
        service.addEmail(email).enqueue(object : Callback<EmailUser> {
            override fun onResponse(call: Call<EmailUser>, response: Response<EmailUser>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<EmailUser>, t: Throwable) {
                onError(t)
            }
        })
    }
}