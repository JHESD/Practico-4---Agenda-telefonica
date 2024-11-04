package com.example.agendatelefonica.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agendatelefonica.Models.PersonasLista
import com.example.agendatelefonica.Repositories.Model_Repositories.PersonUsrRetro

class MainViewModel: ViewModel() {
    private val _contactList: MutableLiveData<PersonasLista> by lazy {
        MutableLiveData(PersonasLista())
    }
    val usrList: LiveData<PersonasLista> get() = _contactList

    fun contactList() {
        PersonUsrRetro.getPersonList(
            onSuccess = { personUsers ->
                personUsers?.let {
                    // Ordenar la lista en base al `id` en orden descendente
                    val sortedList = it.sortedByDescending { contact -> contact.id }
                    // Crear una nueva lista para evitar problemas de referencia
                    val updatedList = PersonasLista()
                    updatedList.addAll(sortedList)
                    _contactList.value = updatedList
                }
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

}

