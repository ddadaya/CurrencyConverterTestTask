package com.vm.vktesttask.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vm.vktesttask.R
import com.vm.vktesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val valuesViewModel by lazy{
        ViewModelProvider(this)[ValuesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = valuesViewModel
        binding.lifecycleOwner = this

        valuesViewModel.valuesList.observe(this){ items ->
            setUpDropDownList(items.map { it.charCode}.sorted())
        }

        binding.button.setOnClickListener {
            valuesViewModel.convertCurrency(
                binding.spinnerFrom.selectedItem.toString(),
                binding.spinnerTo.selectedItem.toString(),
                binding.etAmount.text.toString().toDouble()
            )
        }
    }

    private fun setUpDropDownList(spinnerList: List<String>) {
        val adapter = ArrayAdapter(this, R.layout.spinner_item, spinnerList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter
        binding.spinnerFrom.setSelection(spinnerList.indexOf(getString(R.string.start_value_from)))
        binding.spinnerTo.setSelection(spinnerList.indexOf(getString(R.string.start_value_to)))
    }
}