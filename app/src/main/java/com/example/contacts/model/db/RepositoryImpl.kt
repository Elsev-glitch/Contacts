package com.example.contacts.model.db

import com.example.contacts.model.entity.Contact

class RepositoryImpl(private val dao: AppRoomDao) : Repository {
    override suspend fun getContacts(): MutableList<Contact> {
        return dao.getContacts()
    }

    override suspend fun insertContact(contact: Contact, onSuccess: () -> Unit) {
        dao.insertContact(contact)
        onSuccess()
    }

    override suspend fun updateContact(contact: Contact, onSuccess: () -> Unit) {
        dao.updateContact(contact)
        onSuccess()
    }

    override suspend fun deleteContact(contact: Contact, onSuccess: () -> Unit) {
        dao.delete(contact)
        onSuccess()
    }
}