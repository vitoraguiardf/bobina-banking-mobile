package com.vitoraguiardf.bobinabanking.utils.viewbinding

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractRecyclerViewAdapter<Type, ViewBinder: androidx.viewbinding.ViewBinding>(
    val context: Context,
    private val items: Array<Type>,
):
    RecyclerView.Adapter<AbstractRecyclerViewAdapter.ViewBinderHolder<Type, ViewBinder>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBinderHolder<Type, ViewBinder> {
        return viewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewBinderHolder<Type, ViewBinder>, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { _ ->
            onItemClickListener?.let { it(holder.viewBinder, items[position]) }
        }
        holder.itemView.setOnLongClickListener { _ ->
            return@setOnLongClickListener onItemLongClickListener.let {
                if (it!=null)
                    it(holder.viewBinder, items[position])
                else
                    false
            }
        }
    }

    protected abstract fun viewHolder(parent: ViewGroup?): ViewBinderHolder<Type, ViewBinder>

    abstract class ViewBinderHolder<Type, ViewBinder: androidx.viewbinding.ViewBinding>
        (val viewBinder: ViewBinder): RecyclerView.ViewHolder(viewBinder.root) {
        abstract fun bind(item: Type)
    }

    private var onItemClickListener: ((view: ViewBinder, item: Type)->Unit)? = null
    private var onItemLongClickListener: ((view: ViewBinder, item: Type)->Boolean)? = null

    fun setOnItemClickListener(onItemClickListener: ((view: ViewBinder, item: Type)->Unit)) {
        this.onItemClickListener = onItemClickListener
    }
    fun setOnItemLongClickListener(onItemLongClickListener: ((view: ViewBinder, item: Type)->Boolean)) {
        this.onItemLongClickListener = onItemLongClickListener
    }

}