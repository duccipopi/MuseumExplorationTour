package br.duccipopi.met.model

import androidx.lifecycle.LiveData
import br.duccipopi.met.model.data.MetMuseumDao
import br.duccipopi.met.model.remote.MetMuseumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IRepository

class Repository(private val dao: MetMuseumDao, private val service: MetMuseumApi) : IRepository {

    suspend fun refreshDepartments() {
        withContext(Dispatchers.IO) {
            val departmentResult = service.getDepartments()

            departmentResult.departments.forEach { department ->
                dao.insert(department)
            }
        }
    }

    suspend fun getDepartments(): LiveData<List<Department>> {
        return withContext(Dispatchers.IO) {
            dao.getDepartments()
        }
    }

    suspend fun refreshArtworks(departmentId: Int) {
        withContext(Dispatchers.IO) {
            val result = service.getArtworksIds(departmentId)

            result.ids?.forEach { id ->
                val artwork = service.getArtwork(id)
                artwork.departmentId = departmentId
                dao.insert(artwork)
            }
        }
    }

    suspend fun getArtworkForDepartment(departmentId: Int): LiveData<List<Artwork>> {
        return withContext(Dispatchers.IO) {
            dao.getArtworksFromDepartment(departmentId)
        }
    }

    suspend fun getArtwork(id: Int): LiveData<Artwork> {
        return withContext(Dispatchers.IO) {
            dao.getArtwork(id)
        }
    }


}