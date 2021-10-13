package com.bookandroid.businesscard.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.bookandroid.businesscard.data.model.BusinessCard

@Dao
interface Dao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM businesscard WHERE id = :id")
    fun getById(id: Int): LiveData<BusinessCard>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete
    suspend fun delete(businessCard: BusinessCard)

    @Update
    fun update(businessCard: BusinessCard): Int
}