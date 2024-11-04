package com.example.agendatelefonica.Repositories.Model_Repositories

import com.example.agendatelefonica.API.JSON_PHS
import com.example.agendatelefonica.Models.PersonUser
import com.example.agendatelefonica.Models.PersonasLista
import com.example.agendatelefonica.Repositories.RetrofitRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PersonUsrRetro {
    private val service = RetrofitRepo.getRetrofitInstance()
        .create(JSON_PHS::class.java)

    // Obtener lista de personas
    fun getPersonList(
        onSuccess: (PersonasLista?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepo.getRetrofitInstance()
        val service: JSON_PHS = retrofit.create(JSON_PHS::class.java)

        service.getContactList().enqueue(object : Callback<PersonasLista> {
            override fun onResponse(call: Call<PersonasLista>, response: Response<PersonasLista>) {
                if (response.isSuccessful) {
                    val postlist = response.body()
                    onSuccess(postlist)
                } else {
                    onError(Throwable("Error en la respuesta: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<PersonasLista>, t: Throwable) {
                onError(t)
            }
        })
    }


    // Obtener persona por ID
    fun getPersonById(
        id: Int,
        onSuccess: (PersonUser) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val retrofit = RetrofitRepo.getRetrofitInstance()
        val service = retrofit.create(JSON_PHS::class.java)
        service.getContactById(id).enqueue(object : Callback<PersonUser> {
            override fun onResponse(call: Call<PersonUser>, response: Response<PersonUser>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }
            override fun onFailure(call: Call<PersonUser>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Agregar una nueva persona
    fun addPerson(person: PersonUser, onSuccess: (PersonUser) -> Unit, onError: (Throwable) -> Unit) {
        service.addContact(person).enqueue(object : Callback<PersonUser> {
            override fun onResponse(call: Call<PersonUser>, response: Response<PersonUser>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<PersonUser>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Actualizar persona
    fun updatePerson(id: Int, person: PersonUser, onSuccess: (PersonUser) -> Unit, onError: (Throwable) -> Unit) {
        service.updateContact(id, person).enqueue(object : Callback<PersonUser> {
            override fun onResponse(call: Call<PersonUser>, response: Response<PersonUser>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<PersonUser>, t: Throwable) {
                onError(t)
            }
        })
    }

    // Eliminar persona
    fun deletePerson(id: Int, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        service.deleteContact(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }
        })
    }
}