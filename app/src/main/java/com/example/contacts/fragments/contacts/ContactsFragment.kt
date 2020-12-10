package com.example.contacts.fragments.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.model.entity.Contact
import kotlinx.android.synthetic.main.fragment_contacts.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ContactsFragment : MvpAppCompatFragment(), ContactsView {

    @InjectPresenter
    lateinit var presenter: ContactsPresenter

    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycleView = recycle_view
        adapter = ContactsAdapter { onClick(it) }
        recycleView.adapter = adapter
    }

    private fun onClick(contact: Contact) {
        val action =
            ContactsFragmentDirections.actionContactsFragmentToChangeContactFragment(contact)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }


    override fun onStart() {
        super.onStart()
        btn_add.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToAddContactFragment()
            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        }
    }

    override fun showContacts(listContacts: MutableList<Contact>) {
        adapter.submitList(listContacts)
    }
}