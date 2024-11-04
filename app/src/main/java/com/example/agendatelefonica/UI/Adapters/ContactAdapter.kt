package com.example.agendatelefonica.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.agendatelefonica.Models.PersonUser
import com.example.agendatelefonica.Models.PersonasLista
import com.example.agendatelefonica.databinding.ItemContactUserBinding

class ContactAdapter(
    private var contactList: PersonasLista,
    private var listener: OnContactClickListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact,listener)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateData(newContactList: PersonasLista?) {
        newContactList?.let {
            contactList.clear()
            contactList.addAll(newContactList)
            notifyDataSetChanged()
        }
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: PersonUser,listener: OnContactClickListener) {
            val binding = ItemContactUserBinding.bind(itemView)
            binding.apply {
                txtContactName.text = contact.name
                txtContactLastName.text = contact.last_name
                Glide.with(itemView.context).load(contact.profile_picture).into(imgContactUsr)
                root.setOnClickListener{listener.onContactClick(contact)}
            }
        }
    }

    interface OnContactClickListener{
        fun onContactClick(personUser: PersonUser)
        fun onContactDelete(personUser: PersonUser)
    }
}


