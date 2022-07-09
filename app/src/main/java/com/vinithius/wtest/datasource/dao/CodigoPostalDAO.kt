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
    fun getSize() : Int

    @Query("SELECT * FROM codigo_postal")
    fun getAll(): List<CodigoPostalEntity>

    @Query("SELECT * FROM codigo_postal WHERE nome_localidade LIKE '%' + :name + '%'")
    fun getByNameOrCode(name: String): List<CodigoPostalEntity>

}