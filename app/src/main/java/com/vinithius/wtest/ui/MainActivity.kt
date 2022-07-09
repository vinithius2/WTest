package com.vinithius.wtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vinithius.wtest.R
import com.vinithius.wtest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WTestViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observerCodigoPostal()
        observerLoadingStatus()
        observerLoadingCount()
        observerLoadingSize()
        observerIsProgress()
        with(viewModel) {
            getCodigoPostal()
        }
    }

    private fun observerCodigoPostal() {
        viewModel.codigoPostal.observe(this) { codigoPostal ->
            codigoPostal?.size
        }
    }

    private fun observerLoadingCount() {
        viewModel.codigoPostalCount.observe(this) { count ->
            binding.progress.setCount(count)
        }
    }

    private fun observerLoadingSize() {
        viewModel.codigoPostalSize.observe(this) { size ->
            binding.progress.setSize(size)
        }
    }

    private fun observerLoadingStatus() {
        viewModel.codigoPostalStatus.observe(this) { status ->
            binding.progress.setStatus(status)
        }
    }

    private fun observerIsProgress() {
        viewModel.codigoPostalIsProgress.observe(this) { isProgress ->
            binding.progress.setIsProgress(isProgress)
        }
    }
}