package ir.roela.countersaver.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.roela.countersaver.R
import ir.roela.countersaver.adapter.CounterListAdapter

class CountListFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var recyclerView: RecyclerView? = null
    private var adapter: CounterListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = root.findViewById(R.id.counterList)
        adapter = CounterListAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context,linearLayoutManager.orientation)
        ContextCompat.getDrawable(requireContext(),R.drawable.list_divider)?.let { drawable ->
            dividerItemDecoration.setDrawable(drawable)
        }
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        dashboardViewModel.counters.observe(viewLifecycleOwner, Observer { counters ->
            adapter!!.setItems(counters)
            adapter!!.notifyDataSetChanged()
        })

        return root
    }
}