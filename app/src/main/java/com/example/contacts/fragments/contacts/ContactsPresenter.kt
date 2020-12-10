package com.example.contacts.fragments.contacts

import com.example.contacts.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ContactsPresenter() : MvpPresenter<ContactsView>() {

    override fun onFirstViewAttach() {
        presenterScope.launch(Dispatchers.IO) {
            val contacts = MainActivity.repository.getContacts()
            viewState.showContacts(contacts)
        }
    }
}