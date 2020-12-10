package com.example.contacts.fragments.contacts

import com.example.contacts.model.entity.Contact
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ContactsView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showContacts(listContacts: MutableList<Contact>)
}