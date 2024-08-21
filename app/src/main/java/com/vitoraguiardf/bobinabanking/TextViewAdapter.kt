package com.vitoraguiardf.bobinabanking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vitoraguiardf.bobinabanking.databinding.TextViewBinding

class TextViewAdapter<DataModel>(context: Context, items: MutableList<DataModel>) :
    AbstractViewBinderAdapter<DataModel, TextViewBinding>(context, items) {
    override fun viewHolder(parent: ViewGroup?): ViewBinderHolder<DataModel, TextViewBinding> {
        val viewBinder: TextViewBinding = TextViewBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        return object: ViewBinderHolder<DataModel, TextViewBinding>(viewBinder) {
            override fun bind(item: DataModel) {
                viewBinder.textView.text = item.toString().trim()
            }
        }
    }
}
