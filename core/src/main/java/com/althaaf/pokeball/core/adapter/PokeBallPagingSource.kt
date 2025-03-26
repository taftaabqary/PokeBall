package com.althaaf.pokeball.core.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.althaaf.pokeball.core.data.network.ApiService
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.utils.MapConverter

class PokeBallPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, PokeBall>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeBall> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val offset = if(page == 1) 0 else page * 10

            val response = apiService.getListPokemon(
                limit = 10,
                offset = offset
            )

            val responseList = response.body()?.results?.let {
                MapConverter.listResponseToDataDomain(it)
            }


            LoadResult.Page(
                data = responseList ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseList?.isEmpty() == true) null else page + 1
            )
        } catch (e: Exception) {
            Log.d("LearningPagingSource", e.message.toString())
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokeBall>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}