package br.duccipopi.met.model.remote

import br.duccipopi.met.model.Artwork
import br.duccipopi.met.model.DepartmentResult
import br.duccipopi.met.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetMuseumApi {
    companion object {
        private const val VERSION = "v1"
        const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/$VERSION/"
    }

    @GET("objects")
    suspend fun getAvailableArtworksIds(): SearchResult

    @GET("objects")
    suspend fun getArtworksIds(@Query("departmentIds") departmentId: Int): SearchResult

    @GET("departments")
    suspend fun getDepartments(): DepartmentResult

    @GET("objects/{objectID}")
    suspend fun getArtwork(@Path("objectID") id: Int): Artwork

    @GET("search?q=")
    suspend fun getArtworksIdFromCountry(@Query("geoLocation") country: String): SearchResult

}