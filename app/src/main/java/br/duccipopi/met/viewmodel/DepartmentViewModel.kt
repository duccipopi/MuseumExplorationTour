package br.duccipopi.met.viewmodel

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import br.duccipopi.met.model.Department
import br.duccipopi.met.model.IRepository
import kotlinx.coroutines.launch

class DepartmentViewModel(val app: Application, val repository: IRepository) : AndroidViewModel(app) {

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean>
        get() = _loading


    lateinit var departments: LiveData<List<Department>>

    init {
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

class DepartmentAdapter(private val lifecycleOwner: LifecycleOwner, private val list: LiveData<List<Department>>, private val callback: (Int) -> Unit? = { }) :
    RecyclerView.Adapter<DepartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView

        return DepartmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        list.value?.let {
            holder.bind(it[position], callback)
        }
    }

    override fun getItemCount(): Int {
        return if (list.value != null) list.value!!.size else 0
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        list.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }

}

class DepartmentViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(item: Department, callback: (Int) -> Unit?) {
        view.text = item.toString()
        view.setOnClickListener { callback(item.id) }
    }
}
