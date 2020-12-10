package com.example.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.contacts.model.db.AppRoomDatabase
import com.example.contacts.model.db.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null

    companion object {
        lateinit var repository: RepositoryImpl
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        title = getString(R.string.contacts)

        initDatabase()
    }

    private fun initDatabase() {
        val dao = AppRoomDatabase.getDatabase(applicationContext).getDao()
        repository = RepositoryImpl(dao)
    }
}