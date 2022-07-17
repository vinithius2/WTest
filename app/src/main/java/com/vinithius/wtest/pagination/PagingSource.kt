package com.vinithius.wtest.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinithius.wtest.datasource.dao.CodigoPostalDAO
import com.vinithius.wtest.datasource.models.CodigoPostalEntity

class PagingSource(
    private val codigoPostalDAO: CodigoPostalDAO,
    private val search: String? = null
) :
    PagingSource<Int, CodigoPostalEntity>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CodigoPostalEntity> {
        val page = params.key ?: INITIAL_PAGE_INDEX

        return try {
            val entities = if (search != null) {
                codigoPostalDAO.getByNameOrCode(search, params.loadSize, page * params.loadSize)
            } else {
                codigoPostalDAO.getAll(params.loadSize, page * params.loadSize)
            }
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CodigoPostalEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}