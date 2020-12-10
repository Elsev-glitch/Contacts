package com.example.contacts.fragments.add_contact

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.contacts.R
import com.example.contacts.model.entity.Contact
import com.example.contacts.utilits.REQUEST_PHOTO
import com.example.contacts.utilits.loadPhoto
import kotlinx.android.synthetic.main.fragment_add_contact.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AddContactFragment : MvpAppCompatFragment(), AddContactView {

    @InjectPresenter
    lateinit var presenter: AddContactPresenter

    private var photoUrl: String = "empty"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_photo.setOnClickListener {
            loadPhoto()
        }

        add_btn.setOnClickListener {
            val name = add_name.text.toString()
            val phone = add_phone.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_name), Toast.LENGTH_SHORT).show()
            } else {
                presenter.addContact(Contact(name = name, phone = phone, photoUrl = photoUrl))
            }
        }
    }

    private fun loadPhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK && data != null) {
            photoUrl = data.data.toString()
            add_photo.loadPhoto(photoUrl)
        }
    }

    override fun onBack() {
        val action = AddContactFragmentDirections.actionAddContactFragmentToContactsFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

}