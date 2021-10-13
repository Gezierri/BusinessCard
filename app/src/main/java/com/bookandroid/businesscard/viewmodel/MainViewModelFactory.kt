package com.bookandroid.businesscard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookandroid.businesscard.data.repository.BusinessRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: BusinessRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow viewmodel class")
    }
}