package com.bookandroid.businesscard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookandroid.businesscard.data.model.BusinessCard
import com.bookandroid.businesscard.databinding.ActivityAddBinding
import com.bookandroid.businesscard.viewmodel.MainViewModel
import com.bookandroid.businesscard.viewmodel.MainViewModelFactory

class AddActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as AppBusinessCard).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickListener()
    }

    private fun clickListener(){
        with(binding){
            btnClose.setOnClickListener {
                finish()
            }
            btnConfirm.setOnClickListener {
                var textColor = binding.edCor.editableText.toString()
                if(textColor.isNotEmpty()){
                    textColor
                }
                else{
                    textColor = "#FFFFFFFF"
                }
                val businessCard = BusinessCard(
                    nome = binding.edNome.editableText.toString(),
                    email = binding.edEmail.editableText.toString(),
                    empresa = binding.edEmpresa.editableText.toString(),
                    telefone = binding.edTelefone.editableText.toString(),
                    fundoPersonalizado = textColor
                )
                viewModel.insert(businessCard)
                finish()
            }
        }
    }


}