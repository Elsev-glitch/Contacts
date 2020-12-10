package com.example.contacts.fragments.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.model.entity.Contact
import com.example.contacts.utilits.loadPhoto
import kotlinx.android.synthetic.main.contacts_item.view.*

class ContactsAdapter(private val click: (contact: Contact) -> Unit) :
    ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(DiffCallBack()) {

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                click(getItem(adapterPosition))
            }
        }

        fun bind(item: Contact) = with(itemView) {
            contact_image.loadPhoto(item.photoUrl)
            contact_name.text = item.name
            contact_phone.text = item.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}