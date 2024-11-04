package com.example.agendatelefonica.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendatelefonica.Models.PhoneUser
import com.example.agendatelefonica.R
import com.example.agendatelefonica.databinding.ItemPhoneBinding

class ContactDetail(
    private val phones: ArrayList<PhoneUser>,
    private val listener: OnPhoneClickListener
) : RecyclerView.Adapter<ContactDetail.PhoneViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PhoneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_phone, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phones[position], listener)
    }

    fun addPhones(newPhones: List<PhoneUser>) {
        phones.addAll(newPhones)
        notifyDataSetChanged()
    }

    fun updatePhones(newPhones: List<PhoneUser>) {
        phones.clear()
        phones.addAll(newPhones)
        notifyDataSetChanged()
    }

    class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPhoneBinding.bind(itemView)

        fun bind(phoneUser: PhoneUser, listener: OnPhoneClickListener) {
            binding.txtLabel.text = phoneUser.label
            binding.txtPhoneUsr.setText(phoneUser.number)
            binding.bttSelectLabel.setOnClickListener {
                listener.onPhoneClick(phoneUser)
            }
        }
    }

    interface OnPhoneClickListener {
        fun onPhoneClick(phoneUser: PhoneUser)
    }
}
