package com.example.agendatelefonica.UI.Activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agendatelefonica.Models.PersonUser
import com.example.agendatelefonica.R

class RegEditContactList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_edit_contact_list)
        if (savedInstanceState == null){
            val person = intent.getSerializableExtra("phoneUser") as PersonUser?
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegEditContactList.newInstance(person))
                .commit()
        }
    }
}