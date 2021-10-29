package br.duccipopi.met.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T>(
    private val lifecycleOwner: LifecycleOwner,
    private val list: LiveData<List<T>>,
    private val callback: (T) -> Unit? = { }
) :
    RecyclerView.Adapter<GenericViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView

        return GenericViewHolder(view)
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

        list.observe (lifecycleOwner, {
            notifyDataSetChanged()
        })
    }
}

class GenericViewHolder<T>(private val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(item: T, callback: (T) -> Unit?) {
        view.text = item.toString()
        view.setOnClickListener { callback(item) }
    }
}