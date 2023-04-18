package com.ac10.currencyexchange.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ac10.currencyexchange.R
import com.ac10.currencyexchange.data.CurrencyValue


class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var currencies: List<CurrencyValue>? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var amount: TextView = itemView.findViewById(R.id.amount)
        var symbol: TextView = itemView.findViewById(R.id.symbol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_currency_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > itemCount) {
            return
        }
        val currencyValue = currencies!![position]

        holder.amount.text = String.format("%.2f", currencyValue.amount)
        holder.symbol.text = currencyValue.symbol

    }

    override fun getItemCount(): Int {
        return if (currencies == null) 0 else currencies!!.size
    }

    // we are changing the entire data, we can use suppress the lint
    @SuppressLint("NotifyDataSetChanged")
    fun setData(currencies: List<CurrencyValue>) {
        this.currencies = currencies
        notifyDataSetChanged()
    }

}