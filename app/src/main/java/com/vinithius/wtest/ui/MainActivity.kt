package com.vinithius.wtest.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinithius.wtest.R
import com.vinithius.wtest.databinding.ActivityMainBinding
import com.vinithius.wtest.datasource.models.CodigoPostalData
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: WTestViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observerCodigoPostal()
        observerStatusLoading()
        permissions()
    }

    private fun permissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getIfPermission()) {
                viewModel.getCodigoPostal()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    0
                )
                permissions()
            }
        }
    }

    private fun getIfPermission(): Boolean {
        val write = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val read = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        return write && read
    }

    private fun observerCodigoPostal() {
        viewModel.codigoPostal.observe(this) {
            with(binding) {
                recyclerViewCodigoPostal.isVisible = true
                progress.isVisible = false
                it?.let { listCodigo ->
                    setAdapter(listCodigo)
                }
            }
        }
    }

    private fun observerStatusLoading() {
        viewModel.codigoPostalStatusLoading.observe(this) { loadingData ->
            binding.progress.setData(loadingData)
        }
    }

    private fun setAdapter(codigoPostalList: List<CodigoPostalData>) {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        with(binding) {
            recyclerViewCodigoPostal.layoutManager = linearLayout
            val adapter = CodigoPostalAdapter(codigoPostalList)
            recyclerViewCodigoPostal.adapter = adapter
        }
    }
}
