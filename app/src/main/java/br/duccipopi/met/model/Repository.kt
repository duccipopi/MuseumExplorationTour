package br.duccipopi.met.model

import androidx.lifecycle.LiveData
import br.duccipopi.met.model.data.MetMuseumDao
import br.duccipopi.met.model.remote.MetMuseumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.min

interface IRepository {
    suspend fun refreshDepartments()
    suspend fun getDepartments(): LiveData<List<Department>>
    suspend fun refreshArtworks(departmentId: Int)
    suspend fun refreshArtworks(departmentId: Int, count: Int)
    suspend fun getArtworkForDepartment(departmentId: Int): LiveData<List<Artwork>>
    suspend fun getArtwork(id: Int): LiveData<Artwork>
}

class Repository(private val dao: MetMuseumDao, private val service: MetMuseumApi) : IRepository {

    override suspend fun refreshDepartments() {
        withContext(Dispatchers.IO) {
            val departmentResult = try {
                service.getDepartments()
            } catch (e: Exception) {
                DepartmentResult(emptyList())
            }

            dao.insertAllDepartments(departmentResult.departments)
        }
    }

    override suspend fun getDepartments(): LiveData<List<Department>> {
        return withContext(Dispatchers.IO) {
            dao.getDepartments()
        }
    }

    override suspend fun refreshArtworks(departmentId: Int) {
        withContext(Dispatchers.IO) {
            val result = try {
                service.getArtworksIds(departmentId)
            } catch (e: Exception) {
                SearchResult(0, emptyList())
            }

            val artworks = mutableListOf<Artwork>()
            result.ids?.forEach { id ->
                try {
                    val artwork = service.getArtwork(id)
                    artwork.departmentId = departmentId
                    artworks.add(artwork)
                } finally {

                }
            }

            dao.insertAllArtworks(artworks)
        }
    }

    override suspend fun refreshArtworks(departmentId: Int, count: Int) {
        withContext(Dispatchers.IO) {
            val result = try {
                service.getArtworksIds(departmentId)
            } catch (e: Exception) {
                SearchResult(0, emptyList())
            }

            val artworks = mutableListOf<Artwork>()
            result.ids?.let { list ->
                list.shuffled().subList(0, min(count, list.size))
                    .forEach { id ->
                        try {
                            val artwork = service.getArtwork(id)
                            artwork.departmentId = departmentId
                            artworks.add(artwork)
                        } finally {

                        }
                    }
            }

            dao.insertAllArtworks(artworks)
        }
    }

    override suspend fun getArtworkForDepartment(departmentId: Int): LiveData<List<Artwork>> {
        return withContext(Dispatchers.IO) {
            dao.getArtworksFromDepartment(departmentId)
        }
    }

    override suspend fun getArtwork(id: Int): LiveData<Artwork> {
        return withContext(Dispatchers.IO) {
            dao.getArtwork(id)
        }
    }


}