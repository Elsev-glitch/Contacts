package com.example.contacts.model.db

import com.example.contacts.model.entity.Contact

interface Repository {
    suspend fun getContacts(): MutableList<Contact>
    suspend fun insertContact(contact: Contact, onSuccess: () -> Unit)
    suspend fun updateContact(contact: Contact, onSuccess: () -> Unit)
    suspend fun deleteContact(contact: Contact, onSuccess: () -> Unit)
}