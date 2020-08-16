package ir.roela.countersaver.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import ir.roela.countersaver.model.Counter

class CountListFragment : Fragment() {

    private lateinit var dashboardViewModel: CounterViewModel
    private var lytNoCount: LinearLayout? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: CounterListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        lytNoCount = root.findViewById(R.id.lytNoCount)
        recyclerView = root.findViewById(R.id.counterList)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        ContextCompat.getDrawable(requireContext(), R.drawable.list_divider)?.let { drawable ->
            dividerItemDecoration.setDrawable(drawable)
        }
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        dashboardViewModel.getCounters()?.observe(viewLifecycleOwner,
            Observer<List<Counter>> { counters ->
                try {
                    adapter = CounterListAdapter(requireActivity().application, counters)
                    recyclerView!!.adapter = adapter
                    if (counters.isNotEmpty())
                        lytNoCount?.visibility = View.GONE
                    else lytNoCount?.visibility = View.VISIBLE
                } catch (e: Exception) {
                    lytNoCount?.visibility = View.GONE
                    Log.e("counter", e.message.toString())
                }
            })

        return root
    }
}