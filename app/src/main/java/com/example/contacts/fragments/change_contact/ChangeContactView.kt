package com.example.contacts.fragments.change_contact

import com.example.contacts.model.entity.Contact
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChangeContactView : MvpView {
    fun showContact(contact: Contact)
    fun replace()
}