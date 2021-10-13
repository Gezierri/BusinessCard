package com.bookandroid.businesscard.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookandroid.businesscard.data.model.BusinessCard
import com.bookandroid.businesscard.databinding.RowListBinding

class BusinessCardAdapter : RecyclerView.Adapter<BusinessCardAdapter.MyViewHolder>() {

    var listenerShare: (View) -> Unit = {}

    inner class MyViewHolder(private val binding: RowListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            with(binding) {
                tvNome.text = item.nome
                tvTelefone.text = item.telefone
                tvEmail.text = item.email
                tvNomeEmpresa.text = item.empresa
                try {
                    cardView.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
                } catch (e: IllegalArgumentException) {
                    Log.e("ERROR", e.toString())
                }

                cardView.setOnClickListener {
                    listenerShare(it)
                }
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<BusinessCard>() {
        override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowListBinding.inflate(view, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
