package com.vinithius.wtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vinithius.wtest.R
import com.vinithius.wtest.databinding.ViewholderCodigoPostalBinding
import com.vinithius.wtest.datasource.models.CodigoPostalData

class CodigoPostalAdapter(
    private val codigoPostalList: List<CodigoPostalData>
) : RecyclerView.Adapter<CodigoPostalAdapter.CodigoPostalViewHolder>() {

    private lateinit var binding: ViewholderCodigoPostalBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodigoPostalViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.viewholder_codigo_postal,
            parent,
            false
        )
        return CodigoPostalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CodigoPostalViewHolder, position: Int) {
        holder.bind(codigoPostalList[position])
    }

    override fun getItemCount() = codigoPostalList.size

    inner class CodigoPostalViewHolder(private val binding: ViewholderCodigoPostalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(codigoPostal: CodigoPostalData) {
            with(binding) {
                val name = "${codigoPostal.extCodPostal} - ${codigoPostal.desigPostal}"
                textView.text = name
            }
        }
    }
}
