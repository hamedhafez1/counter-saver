package ir.roela.countersaver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.roela.countersaver.R
import ir.roela.countersaver.model.Counter

class CounterListAdapter() :
    RecyclerView.Adapter<CounterHolder>() {

    private var countersList:List<Counter>? = null
    private var layoutInflater: LayoutInflater? = null

    public fun setItems(arrayList: List<Counter>) {
        countersList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val v = layoutInflater?.inflate(R.layout.item_layout, parent, false)
        return CounterHolder(v!!)
    }

    override fun getItemCount(): Int {
        return countersList!!.size
    }

    override fun onBindViewHolder(holder: CounterHolder, position: Int) {
        holder.txtCounterName?.text = countersList?.get(position)?.countName
        holder.txtCounterValue?.text = countersList?.get(position)?.countValue
    }
}