package com.vitoraguiardf.bobinabanking.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.Transaction
import com.vitoraguiardf.bobinabanking.databinding.LayoutTransactionItemBinding
import com.vitoraguiardf.bobinabanking.utils.adapters.AbstractViewBinderAdapter
import com.vitoraguiardf.bobinabanking.utils.datetime.TimeUtils
import java.util.Locale

class TransactionAdapter(context: Context, items: Array<Transaction>):
    AbstractViewBinderAdapter<Transaction, LayoutTransactionItemBinding>(context, items) {

    override fun viewHolder(parent: ViewGroup?): ViewBinderHolder<Transaction, LayoutTransactionItemBinding> {
        val binder = LayoutTransactionItemBinding.inflate(
            LayoutInflater.from(parent!!.context), parent, false)
        return object: ViewBinderHolder<Transaction, LayoutTransactionItemBinding>(binder) {
            override fun bind(item: Transaction) {
                binder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_up))

                binder.textViewType.text = "${item.transactionTypeId}"
                binder.textViewName.text = "De: ${item.fromStorageId} Para: ${item.toStorageId}"
                binder.textViewValue.text = String.format(Locale.getDefault(),
                    "%,2d %s",
                    item.quantity, context.getString(R.string.unities)
                )
                binder.textViewTime.text = TimeUtils.from.laravel(item.createdAt).toHours()
            }
        }
    }

}