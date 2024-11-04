package com.example.agendatelefonica.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.agendatelefonica.Models.PhoneUser
import com.example.agendatelefonica.R
import com.example.agendatelefonica.databinding.ItemPhoneBinding

class PhoneAdapter(
    private val phoneList: ArrayList<PhoneUser>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PhoneViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_phone, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phoneList[position], listener)
    }

    fun addPhone(phoneUsers: List<PhoneUser>) {
        this.phoneList.addAll(phoneUsers)
        notifyDataSetChanged()
    }

    fun updatePhone(phoneUsers: List<PhoneUser>) {
        this.phoneList.clear()
        this.phoneList.addAll(phoneUsers)
        notifyDataSetChanged()
    }

    class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPhoneBinding.bind(itemView)

        fun bind(phoneUser: PhoneUser, listener: OnItemClickListener) {
            binding.txtLabel.text = phoneUser.label
            binding.txtPhoneUsr.text = phoneUser.number
            binding.bttSelectLabel.setOnClickListener {
                listener.onItemClick(phoneUser)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(phoneUser: PhoneUser)
    }

}
