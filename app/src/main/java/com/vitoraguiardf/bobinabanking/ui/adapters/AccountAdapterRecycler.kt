package com.vitoraguiardf.bobinabanking.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.databinding.CardAccountBinding
import com.vitoraguiardf.bobinabanking.utils.viewbinding.AbstractRecyclerViewAdapter

class AccountAdapterRecycler(context: Context, items: Array<Account>):
    AbstractRecyclerViewAdapter<Account, CardAccountBinding>(context, items) {
    override fun viewHolder(parent: ViewGroup?): ViewBinderHolder<Account, CardAccountBinding> {
        val binder = CardAccountBinding.inflate(
            LayoutInflater.from(parent!!.context), parent, false)
        return object: ViewBinderHolder<Account, CardAccountBinding>(binder) {
            override fun bind(item: Account) {
                binder.username.text = item.holderName.uppercase()
                binder.account.text = item.name.uppercase()
            }
        }
    }
}