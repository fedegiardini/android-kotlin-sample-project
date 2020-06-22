package com.kotlinsampleapp.mainfeature.data

import com.kotlinsampleapp.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author federico.giardini
 */
interface API {
    /**
     * Method that retrieves items given a query
     * @param query Search Query
     * @param site the ML site for the search (in this case hardcoded to "MLA")
     * @param offset offset used for pagination
     * @param limit quantity of items received per call
     * @return an instance of [SearchResponse]
     */
    @GET("{site}/search")
    suspend fun getItems(
        @Path("site") site: String = "MLA",
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): SearchResponse
}