package com.vinithius.wtest.datasource.repository

import android.util.Log
import com.vinithius.wtest.datasource.dao.CodigoPostalDAO
import com.vinithius.wtest.datasource.models.CodigoPostalResponse
import com.vinithius.wtest.datasource.models.Mapper
import com.vinithius.wtest.extension.getResponseByString
import retrofit2.HttpException

class WTestRepository(
    private val remoteDataSource: WTestRemoteDataResource,
    private val codigoPostalDAO: CodigoPostalDAO
) {

    suspend fun getCodigoPostalList(
        loading: (
            (isProgress: Boolean, status: String, count: Int?, size: Int?) -> Unit)
    ): List<CodigoPostalResponse>? {
        return try {
            var count = codigoPostalDAO.getSize()
            if (count == 0) {
                loading.invoke(true, STATUS_NETWORK, 0, null)
                val value = remoteDataSource.getCsv()
                val lines = value.string().split("\n")
                lines.drop(1).forEach { codigoPostal ->
                    loading.invoke(true, STATUS_PROGRESS, count++, lines.size)
                    val response = codigoPostal.split(",").getResponseByString()
                    response?.let {
                        val entity = Mapper().mapToEntity(it)
                        codigoPostalDAO.insertCodigoPostal(entity)
                    }
                }
            }
            loading.invoke(false, STATUS_PROGRESS, null, null)
            return codigoPostalDAO.getAll().map { Mapper().mapFromEntity(it) }
        } catch (e: HttpException) {
            Log.e("CSV ", e.toString())
            null
        }
    }

    companion object {
        const val STATUS_NETWORK = "Baixando dados..."
        const val STATUS_PROGRESS = "Inserindo dados: "
    }

}