package ir.roela.countersaver.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.roela.countersaver.R

class CounterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txtCounterName: TextView? = null
    var txtCounterDate: TextView? = null
    var txtCounterValue: TextView? = null
    var btnDeleteCount: ImageButton? = null

    init {
        txtCounterName = itemView.findViewById(R.id.txtCounterName)
        txtCounterDate = itemView.findViewById(R.id.txtCounterDate)
        txtCounterValue = itemView.findViewById(R.id.txtCounterValue)
        btnDeleteCount = itemView.findViewById(R.id.btnDeleteCount)
        itemView.tag = itemView
    }

}