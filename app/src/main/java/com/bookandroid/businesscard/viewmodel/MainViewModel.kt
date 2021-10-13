package com.bookandroid.businesscard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookandroid.businesscard.data.model.BusinessCard
import com.bookandroid.businesscard.data.repository.BusinessRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BusinessRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard) {
        viewModelScope.launch {
            repository.insert(businessCard)
        }
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return repository.getAll()
    }

    fun delete(businessCard: BusinessCard) {
        viewModelScope.launch {
            repository.delete(businessCard)
        }
    }

}