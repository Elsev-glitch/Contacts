package com.example.contacts.model.db

import androidx.room.*
import com.example.contacts.model.entity.Contact

@Dao
interface AppRoomDao {

    @Query("SELECT * from contacts")
    fun getContacts(): MutableList<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contact: Contact)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateContact(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}