package com.vinithius.wtest.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.vinithius.wtest.databinding.LoadingComponentBinding
import com.vinithius.wtest.datasource.models.LoadingData

class ProgressComponent(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private var status: LoadingData = LoadingData()
        set(value) {
            field = value
            setLoadingData(value)
        }

    private val binding =
        LoadingComponentBinding.inflate(LayoutInflater.from(context), this, true)

    private fun setLoadingData(loadingData: LoadingData) {
        with(binding) {
            if (loadingData.isProgress && loadingData.count > 0) {
                progressBar.isIndeterminate = false
                root.isVisible = true
                val percent = (loadingData.count.toDouble() / loadingData.size) * 100
                progressBar.progress = percent.toInt()
                status.text =
                    "${loadingData.statusText} ${String.format("%.1f", percent)}%"
            } else if (loadingData.isProgress && loadingData.count == 0) {
                root.isVisible = true
                status.text = loadingData.statusText
                progressBar.isIndeterminate = true
            } else {
                root.isVisible = false
            }
        }
    }

    fun setData(loadingData: LoadingData) {
        status = loadingData
    }
}
