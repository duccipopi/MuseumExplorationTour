package br.duccipopi.met.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.duccipopi.met.model.Department
import br.duccipopi.met.model.IRepository
import kotlinx.coroutines.launch

class DepartmentViewModel(val app: Application, val repository: IRepository) :
    AndroidViewModel(app) {

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean>
        get() = _loading


    lateinit var departments: LiveData<List<Department>>

    init {
        loadDepartments()
        viewModelScope.launch {
            departments = repository.getDepartments()
            _loading.value = false
        }
    }


    fun loadDepartments() {
        _loading.value = true
        viewModelScope.launch {
            repository.refreshDepartments()
            _loading.value = false
        }
    }

}
