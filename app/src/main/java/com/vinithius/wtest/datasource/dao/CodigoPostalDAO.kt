package com.vinithius.wtest.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinithius.wtest.datasource.models.CodigoPostalEntity

@Dao
interface CodigoPostalDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCodigoPostal(vararg codigoPostal: CodigoPostalEntity)

    @Query("SELECT count(*) FROM codigo_postal")
    fun getSize(): Int

    @Query("SELECT * FROM codigo_postal LIMIT :limit OFFSET :offset")
    fun getAll(limit: Int, offset: Int): List<CodigoPostalEntity>

    @Query("SELECT * FROM codigo_postal WHERE desig_postal LIKE '%' || :search || '%' LIMIT :limit OFFSET :offset")
    fun getByNameOrCode(search: String, limit: Int, offset: Int): List<CodigoPostalEntity>

}