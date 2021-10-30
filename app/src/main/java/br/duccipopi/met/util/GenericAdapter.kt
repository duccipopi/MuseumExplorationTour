package br.duccipopi.met.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import br.duccipopi.met.BR

class GenericAdapter<T>(
    private val lifecycleOwner: LifecycleOwner,
    private val list: LiveData<List<T>>,
    private val layout: Int,
    private val callback: (T) -> Unit = { }
) :
    RecyclerView.Adapter<GenericViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, parent, false)

        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        list.value?.let {
            holder.bind(it[position], callback)
        }
    }

    override fun getItemCount(): Int {
        return if (list.value != null) list.value!!.size else 0
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        list.observe(lifecycleOwner, {
            notifyDataSetChanged()
        })
    }
}

class GenericViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T, callback: (T) -> Unit) {
        binding.setVariable(BR.data, item)
        binding.setVariable(BR.onClick, callback)
        binding.executePendingBindings()
    }
}