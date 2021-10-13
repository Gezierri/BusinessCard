package com.bookandroid.businesscard.ui

import android.app.Application
import com.bookandroid.businesscard.data.db.AppDatabase
import com.bookandroid.businesscard.data.repository.BusinessRepository

class AppBusinessCard: Application() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessRepository(database.businessDao()) }

}