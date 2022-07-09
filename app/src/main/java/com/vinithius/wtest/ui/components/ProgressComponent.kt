package com.vinithius.wtest.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.vinithius.wtest.databinding.LoadingComponentBinding

class ProgressComponent(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private var sizeProgress: Int = 0
    private var statusProgress: String = ""

    private val binding =
        LoadingComponentBinding.inflate(LayoutInflater.from(context), this, true)

    fun setSize(size: Int) {
        sizeProgress = size
    }

    fun setStatus(status: String) {
        statusProgress = status
    }

    fun setCount(count: Int) {
        with(binding) {
            if (count > 0) {
                val percent = (count.toDouble() / sizeProgress) * 100
                progressBar.isIndeterminate = false
                progressBar.progress = percent.toInt()
                status.text = "$statusProgress ${percent.toInt()}%"
            } else {
                status.text = statusProgress
                progressBar.isIndeterminate = true
            }
        }
    }

    fun setIsProgress(isProgress: Boolean) {
//        binding.root.isGone = !isProgress
    }
}
