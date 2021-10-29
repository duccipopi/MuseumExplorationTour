package br.duccipopi.met.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.duccipopi.met.model.Artwork
import br.duccipopi.met.model.IRepository
import kotlinx.coroutines.launch

class GalleryViewModel(val app: Application, val repository: IRepository) : AndroidViewModel(app) {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    lateinit var artworks: LiveData<List<Artwork>>

    fun loadArtworksForDepartment(id: Int) {
        _loading.value = true
        viewModelScope.launch {
            if (!this@GalleryViewModel::artworks.isInitialized) {
              artworks = repository.getArtworkForDepartment(id)
            }
            repository.refreshArtworks(id, 6)
            _loading.value = false
        }
    }

}