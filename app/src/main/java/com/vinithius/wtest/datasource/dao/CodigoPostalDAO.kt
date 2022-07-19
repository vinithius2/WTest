package com.vinithius.wtest.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinithius.wtest.datasource.models.CodigoPostalEntity

@Dao
interface CodigoPostalDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCodigoPostal(vararg codigoPostal: CodigoPostalEntity)

    @Query("SELECT count(*) FROM codigo_postal")
    suspend fun getSize(): Int

    @Query("SELECT * FROM codigo_postal LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<CodigoPostalEntity>

    @Query(
        """
        SELECT * FROM codigo_postal
        JOIN codigo_postal_fts ON codigo_postal.id = codigo_postal_fts.rowid
        WHERE codigo_postal_fts 
        MATCH :query
        LIMIT :limit OFFSET :offset
    """
    )
    suspend fun getByNameOrCode(query: String, limit: Int, offset: Int): List<CodigoPostalEntity>

}