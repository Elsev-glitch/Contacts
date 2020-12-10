package com.example.contacts.fragments.change_contact

import com.example.contacts.MainActivity
import com.example.contacts.model.entity.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ChangeContactPresenter(private val contact: Contact) : MvpPresenter<ChangeContactView>() {

    override fun onFirstViewAttach() {
        viewState.showContact(contact)
    }

    fun changeContact(contact: Contact) {
        presenterScope.launch(Dispatchers.IO) {
            MainActivity.repository.updateContact(contact) {
                viewState.replace()
            }
        }
    }

    fun deleteContact(contact: Contact) {
        presenterScope.launch(Dispatchers.IO) {
            MainActivity.repository.deleteContact(contact) {
                viewState.replace()
            }
        }
    }
}