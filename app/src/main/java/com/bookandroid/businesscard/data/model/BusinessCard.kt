package com.bookandroid.businesscard.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val fundoPersonalizado: String?,
) : Parcelable