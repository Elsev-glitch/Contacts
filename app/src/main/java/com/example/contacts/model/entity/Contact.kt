package com.example.contacts.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "",
    var phone: String = "",
    var photoUrl: String = ""
) : Serializable