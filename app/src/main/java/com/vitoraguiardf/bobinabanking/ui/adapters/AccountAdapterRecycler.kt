package com.vitoraguiardf.bobinabanking.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.databinding.LayoutTransactionItemBinding
import com.vitoraguiardf.bobinabanking.utils.adapters.AbstractRecyclerViewBinderAdapter

class AccountAdapterRecycler(context: Context, items: Array<Account>):
    AbstractRecyclerViewBinderAdapter<Account, LayoutTransactionItemBinding>(context, items) {
    override fun viewHolder(parent: ViewGroup?): ViewBinderHolder<Account, LayoutTransactionItemBinding> {
        val binder = LayoutTransactionItemBinding.inflate(
            LayoutInflater.from(parent!!.context), parent, false)
        return object: ViewBinderHolder<Account, LayoutTransactionItemBinding>(binder) {
            @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
            override fun bind(item: Account) {
                binder.textViewType.text = item.toString()
            }
        }
    }
}