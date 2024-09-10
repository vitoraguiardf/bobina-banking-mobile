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
            if (onItemClickListener != null) onItemClickListener!!.onClick(
                holder.viewBinder, items[position]
            )
        }
        holder.itemView.setOnLongClickListener { _ ->
            if (onItemLongClickListener != null) onItemLongClickListener!!.onLongClick(
                holder.viewBinder, items[position]
            )
            true
        }
    }

    protected abstract fun viewHolder(parent: ViewGroup?): ViewBinderHolder<Type, ViewBinder>

    abstract class ViewBinderHolder<Type, ViewBinder: androidx.viewbinding.ViewBinding>
        (val viewBinder: ViewBinder): RecyclerView.ViewHolder(viewBinder.root) {
        abstract fun bind(item: Type)
    }

    interface OnItemClickListener<ViewBinder, Type> {
        fun onClick(binder: ViewBinder, item: Type)
    }
    interface OnItemLongClickListener<ViewBinder, Type> {
        fun onLongClick(view: ViewBinder, item: Type)
    }

    private var onItemClickListener: OnItemClickListener<ViewBinder, Type>? = null
    private var onItemLongClickListener: OnItemLongClickListener<ViewBinder, Type>? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<ViewBinder, Type>) {
        this.onItemClickListener = onItemClickListener
    }
    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<ViewBinder, Type>) {
        this.onItemLongClickListener = onItemLongClickListener
    }

}