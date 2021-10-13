package com.bookandroid.businesscard.data.repository

import com.bookandroid.businesscard.data.db.Dao
import com.bookandroid.businesscard.data.model.BusinessCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessRepository(private val dao: Dao) {

    suspend fun insert(businessCard: BusinessCard){
        return dao.insert(businessCard)
    }

    fun getAll() = dao.getAll()

    suspend fun delete(businessCard: BusinessCard) {
        dao.delete(businessCard)
    }
}