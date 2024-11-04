package com.example.agendatelefonica.UI.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendatelefonica.R
import com.example.agendatelefonica.UI.ViewModels.MainViewModel
import com.example.agendatelefonica.databinding.ActivityMainBinding
import com.example.agendatelefonica.Models.PersonUser
import com.example.agendatelefonica.UI.Adapters.ContactAdapter

class MainActivity : AppCompatActivity(), ContactAdapter.OnContactClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.contactList()  // Llama a contactList() para obtener la lista de contactos cuando se reanuda la actividad
    }

    private fun setupViewModelListeners() {
        viewModel.usrList.observe(this) { contactList ->
            (binding.rcyContactList.adapter as ContactAdapter).updateData(contactList)
        }
    }

    private fun setupRecyclerView() {
        binding.rcyContactList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContactAdapter(
                arrayListOf(),
                this@MainActivity
            )
        }
    }

    override fun onContactClick(contact: PersonUser) {
        // Maneja el evento de clic en un contacto
    }

    override fun onContactDelete(contact: PersonUser) {
        // Maneja el evento de eliminación de un contacto
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
