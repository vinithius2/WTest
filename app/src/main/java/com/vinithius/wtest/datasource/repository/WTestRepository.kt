package com.vinithius.wtest.datasource.repository

import android.os.Environment
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.opencsv.CSVReader
import com.vinithius.wtest.datasource.dao.CodigoPostalDAO
import com.vinithius.wtest.datasource.models.LoadingData
import com.vinithius.wtest.extension.getEntityByArray
import com.vinithius.wtest.pagination.PagingSource
import java.io.*

class WTestRepository(
    private val remoteDataSource: WTestRemoteDataResource,
    private val codigoPostalDAO: CodigoPostalDAO
) {

    fun getPagerCodigoPostalList(query: String? = null) =
        Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSource(codigoPostalDAO, query)
            }
        ).liveData

    suspend fun getExistCodigoPostal(): Boolean {
        return codigoPostalDAO.getSize() > 0
    }

    suspend fun getDownloadAndSaveCSV(
        loading: (
            (loadingData: LoadingData) -> Unit)
    ): Boolean {

        loading.invoke(
            LoadingData(
                isProgress = true,
                statusText = STATUS_NETWORK
            )
        )
        val csvFile = remoteDataSource.getCsv().byteStream()
        writeToFileCsv(csvFile)?.let {
            insertDatabase(it, loading)
        }
        loading.invoke(
            LoadingData(
                isProgress = false
            )
        )

        return true
    }

    private fun writeToFileCsv(inputStream: InputStream): String? {
        return try {
            val path: File =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val writer = FileOutputStream(File(path, FILE_NAME))
            writer.write(inputStream.readBytes())
            writer.close()
            "$path/$FILE_NAME"
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend fun insertDatabase(
        path: String, loading: ((loadingData: LoadingData) -> Unit)
    ) {
        return try {
            val reader = CSVReader(FileReader(path))
            val lines = reader.readAll()
            var count = 0
            val size = lines.size
            lines.removeAt(0) // Header
            lines.forEach { line ->
                ++count
                line.getEntityByArray()?.let {
                    codigoPostalDAO.insertCodigoPostal(it)
                }
                loading.invoke(
                    LoadingData(
                        isProgress = true,
                        statusText = STATUS_DATABASE,
                        count = count,
                        size = size
                    )
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val FILE_NAME = "codigos_postais.csv"
        const val STATUS_NETWORK = "Download CSV..."
        const val STATUS_DATABASE = "Inserting in database"
    }

}