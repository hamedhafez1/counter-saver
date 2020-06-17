package ir.roela.countersaver.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.roela.countersaver.R

class CounterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txtCounterName: TextView? = null
    var txtCounterValue: TextView? = null

    init {
        txtCounterName = itemView.findViewById(R.id.txtCounterName)
        txtCounterValue = itemView.findViewById(R.id.txtCounterValue)
        itemView.tag = itemView
    }

}