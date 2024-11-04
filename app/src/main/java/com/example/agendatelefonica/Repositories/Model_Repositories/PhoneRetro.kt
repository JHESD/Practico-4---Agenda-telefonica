package com.example.agendatelefonica.Repositories.Model_Repositories

import com.example.agendatelefonica.API.JSON_PHS
import com.example.agendatelefonica.Models.PhoneUser
import com.example.agendatelefonica.Repositories.RetrofitRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PhoneRetro {
    private val service = RetrofitRepo.getRetrofitInstance()
        .create(JSON_PHS::class.java)

    // Obtener lista de teléfonos
    fun getPhoneList(onSuccess: (List<PhoneUser>) -> Unit, onError: (Throwable) -> Unit) {
        service.getPhoneList().enqueue(object : Callback<List<PhoneUser>> {
            override fun onResponse(call: Call<List<PhoneUser>>, response: Response<List<PhoneUser>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<PhoneUser>>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Agregar teléfono
    fun addPhone(phone: PhoneUser, onSuccess: (PhoneUser) -> Unit, onError: (Throwable) -> Unit) {
        service.addPhone(phone).enqueue(object : Callback<PhoneUser> {
            override fun onResponse(call: Call<PhoneUser>, response: Response<PhoneUser>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<PhoneUser>, t: Throwable) {
                onError(t)
            }
        })
    }
}