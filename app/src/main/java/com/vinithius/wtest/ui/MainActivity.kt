package com.vinithius.wtest.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinithius.wtest.R
import com.vinithius.wtest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: WTestViewModel by viewModel()
    private lateinit var adapter: CodigoPostalAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observerStatusLoading()
        observerIsExistCodigoPostal()
        observerFinishDownloadAndSave()
        setAdapter()
        permissions()
    }

    private fun searchCodigoPostal(query: String? = null) {
        binding.progress.isVisible = false
        lifecycleScope.launch {
            viewModel.getCodigoPostal(query)?.observe(this@MainActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun permissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getIfPermission()) {
                viewModel.getIsExistCodigoPostal()
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

    private fun observerStatusLoading() {
        viewModel.codigoPostalStatusLoading.observe(this) { loadingData ->
            binding.progress.setData(loadingData)
        }
    }

    private fun observerIsExistCodigoPostal() {
        viewModel.isExistCodigoPostal.observe(this) { isExist ->
            if (isExist) {
                searchCodigoPostal()
            } else {
                viewModel.getDownloadAndSaveCSV()
            }
        }
    }

    private fun observerFinishDownloadAndSave() {
        viewModel.finishDownloadAndSave.observe(this) { isFinish ->
            if (isFinish) {
                searchCodigoPostal()
            }
        }
    }

    private fun setAdapter() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        with(binding) {
            recyclerViewCodigoPostal.layoutManager = linearLayout
            adapter = CodigoPostalAdapter()
            recyclerViewCodigoPostal.adapter = adapter
        }
    }

    /**
     * Create a search in toolbar.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        item?.let { menuItem ->
            searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(nameStartsWith: String?): Boolean {
                    nameStartsWith?.let { searchCodigoPostal(it) }
                    return false
                }

                override fun onQueryTextChange(nameStartsWith: String?): Boolean {
                    nameStartsWith?.let {
                        if (it.isEmpty()) {
                            searchCodigoPostal()
                        }
                    }
                    return false
                }
            })
            menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                    searchCodigoPostal()
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}
