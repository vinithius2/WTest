package com.vinithius.wtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vinithius.wtest.R
import com.vinithius.wtest.databinding.ViewholderCodigoPostalBinding
import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.Mapper

class CodigoPostalAdapter :
    PagingDataAdapter<CodigoPostalEntity, CodigoPostalAdapter.CodigoPostalViewHolder>(COMPARATOR) {

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
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class CodigoPostalViewHolder(private val binding: ViewholderCodigoPostalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(codigoPostal: CodigoPostalEntity) {
            val mapper = Mapper().mapFromEntity(codigoPostal)
            with(binding) {
                val name = "${mapper.numCodPostal}-${mapper.extCodPostal} ${mapper.desigPostal}"
                textView.text = name
            }
        }
    }

    companion object {

        private object COMPARATOR : DiffUtil.ItemCallback<CodigoPostalEntity>() {
            override fun areItemsTheSame(
                oldItem: CodigoPostalEntity,
                newItem: CodigoPostalEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CodigoPostalEntity,
                newItem: CodigoPostalEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
