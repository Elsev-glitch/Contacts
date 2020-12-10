package com.example.contacts.fragments.add_contact

import com.example.contacts.MainActivity
import com.example.contacts.model.entity.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class AddContactPresenter : MvpPresenter<AddContactView>() {

    fun addContact(contact: Contact) {
        presenterScope.launch(Dispatchers.IO) {
            MainActivity.repository.insertContact(contact) {
                viewState.onBack()
            }
        }
    }
}