package com.example.mygarden.data.remot

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mygarden.api.PlantService
import com.example.mygarden.data.remot.remoteModel.PlantPhotoDto

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class PlantPhotoPagingSource(
    private val service: PlantService,
    private val plantName: String
) : PagingSource<Int, PlantPhotoDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantPhotoDto> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.searchPhotos(plantName, page, params.loadSize)
            val plantPhotos = response.plants
            LoadResult.Page(
                data = plantPhotos,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlantPhotoDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }


}