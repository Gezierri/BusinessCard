package com.bookandroid.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bookandroid.businesscard.adapter.BusinessCardAdapter
import com.bookandroid.businesscard.databinding.ActivityMainBinding
import com.bookandroid.businesscard.utils.Image
import com.bookandroid.businesscard.viewmodel.MainViewModel
import com.bookandroid.businesscard.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as AppBusinessCard).repository)
    }

    private val businessAdapter: BusinessCardAdapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecyclerView()
        getAllBusinessCard()
        insertListener()
        deleteTouch()
    }

    private fun setRecyclerView() {
        val rcView = binding.recyclerView
        rcView.adapter = businessAdapter
    }

    private fun deleteTouch() {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val businessCard = businessAdapter.differ.currentList[position]
                mainViewModel.delete(businessCard)

                Snackbar.make(
                    findViewById(android.R.id.content),
                    "BusinessCard deletado com sucesso",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction("cancelar") {
                        mainViewModel.insert(businessCard)
                    }
                }.show()

            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    private fun insertListener() {
        binding.fb.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        businessAdapter.listenerShare = {card ->
            Image.share(this@MainActivity, card )
        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, {
            businessAdapter.differ.submitList(it)
        })
    }
}