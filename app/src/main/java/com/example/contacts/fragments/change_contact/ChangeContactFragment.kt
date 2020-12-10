package com.example.contacts.fragments.change_contact

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.contacts.R
import com.example.contacts.model.entity.Contact
import com.example.contacts.utilits.REQUEST_PHOTO
import com.example.contacts.utilits.loadPhoto
import kotlinx.android.synthetic.main.fragment_change_contact.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ChangeContactFragment : MvpAppCompatFragment(), ChangeContactView {

    @InjectPresenter
    lateinit var presenter: ChangeContactPresenter

    private val args: ChangeContactFragmentArgs by navArgs()

    @ProvidePresenter
    fun providePresenter(): ChangeContactPresenter {
        return ChangeContactPresenter(args.contact)
    }

    private var newPhotoUrl: String = "empty"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        change_photo.setOnClickListener {
            updatePhoto()
        }

        change_btn.setOnClickListener {
            updateContact()
        }

        call.setOnClickListener {
            val phone = args.contact.phone
            call(phone)
        }

    }

    private fun call(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        startActivity(intent)
    }

    private fun updateContact() {
        val id = args.contact.id
        val name = change_name.text.toString()
        val phone = change_phone.text.toString()
        presenter.changeContact(
            Contact(
                id = id,
                name = name,
                phone = phone,
                photoUrl = newPhotoUrl
            )
        )
    }

    private fun updatePhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            newPhotoUrl = data.data.toString()
            change_photo.loadPhoto(newPhotoUrl)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                presenter.deleteContact(args.contact)
            }
        }
        return false
    }

    override fun showContact(contact: Contact) {
        change_name.setText(contact.name).toString()
        change_phone.setText(contact.phone).toString()
        change_photo.loadPhoto(contact.photoUrl)
    }

    override fun replace() {
        val action = ChangeContactFragmentDirections.actionChangeContactFragmentToContactsFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

}