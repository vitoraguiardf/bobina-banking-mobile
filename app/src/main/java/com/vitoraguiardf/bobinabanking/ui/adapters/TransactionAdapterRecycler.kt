package com.vitoraguiardf.bobinabanking.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransactionFull
import com.vitoraguiardf.bobinabanking.databinding.LayoutTransactionItemBinding
import com.vitoraguiardf.bobinabanking.utils.adapters.AbstractRecyclerViewBinderAdapter
import com.vitoraguiardf.bobinabanking.utils.datetime.TimeUtils
import java.util.Locale

class TransactionAdapterRecycler(context: Context, items: Array<CoilTransactionFull>):
    AbstractRecyclerViewBinderAdapter<CoilTransactionFull, LayoutTransactionItemBinding>(context, items) {

    override fun viewHolder(parent: ViewGroup?): ViewBinderHolder<CoilTransactionFull, LayoutTransactionItemBinding> {
        val binder = LayoutTransactionItemBinding.inflate(
            LayoutInflater.from(parent!!.context), parent, false)
        return object: ViewBinderHolder<CoilTransactionFull, LayoutTransactionItemBinding>(binder) {
            @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
            override fun bind(item: CoilTransactionFull) {
                binder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_up))

                binder.textViewType.text = item.transactionType.name
                val from = item.fromAccount?.let {
                    String.format("De: %s", it.name)
                }
                val to = item.toAccount?.let {
                    String.format("Para: %s", it.name)
                }
                binder.textViewName.text = "${from?:""}\n${to?:""}".trim()
                binder.textViewValue.text = String.format(Locale.getDefault(),
                    "%,2d %s",
                    item.coilTransaction.quantity, context.getString(R.string.unities)
                )
                binder.textViewTime.text = TimeUtils.from.laravel(item.coilTransaction.createdAt).toHours()
            }
        }
    }

}