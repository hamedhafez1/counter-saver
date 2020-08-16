package ir.roela.countersaver.adapter

import android.app.Application
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import ir.roela.countersaver.R
import ir.roela.countersaver.model.Counter
import ir.roela.countersaver.ui.dashboard.CounterViewModel


class CounterListAdapter(
    private var application: Application,
    private var countersList: List<Counter>
) : RecyclerView.Adapter<CounterHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val v = layoutInflater?.inflate(R.layout.item_layout, parent, false)
        return CounterHolder(v!!)
    }

    override fun getItemCount(): Int {
        return countersList.size
    }

    override fun onBindViewHolder(holder: CounterHolder, position: Int) {
        val name = countersList[position].countName
        holder.txtCounterName?.text = name
        holder.txtCounterValue?.text = countersList[position].countValue
        holder.btnDeleteCount?.setOnClickListener { view ->
            val wrapper = ContextThemeWrapper(application, R.style.PopupMenu)
            val popupMenu = PopupMenu(wrapper, view)
            popupMenu.inflate(R.menu.accept_delete_count)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.acceptDeleteCount -> {
                        CounterViewModel(application).deleteCounter(countersList[position])
                    }
                }
                true
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            popupMenu.show()
            /*val menuHelper = MenuPopupHelper(wrapper, (popupMenu.menu as MenuBuilder), view)
            menuHelper.setForceShowIcon(true)
            menuHelper.show()*/
        }
    }
}