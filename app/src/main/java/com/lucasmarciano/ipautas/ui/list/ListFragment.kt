package com.lucasmarciano.ipautas.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.injection.prefs
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {
    val TAG = Logger.tag

    private val adapter: ListAdapter by inject()
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        setupRecyclerView()
        loadData()

        bnv_lists.setOnNavigationItemSelectedListener { item ->
            progressBar?.visibility = View.VISIBLE
            recyclerView?.visibility = View.GONE
            tv_message?.visibility = View.GONE
            fab_add?.hide()

            when (item.itemId) {
                R.id.navigation_list_active -> loadData()
                R.id.navigation_list_inactive -> loadData(false)
                else -> loadData()
            }

            true
        }

        fab_add.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_newScheduleFragment)
        }
    }

    private fun loadData(isActive: Boolean = true) {
        if (Logger.DEBUG) Log.d(TAG, "loadDataByActive")

        viewModel.getListSchedules(prefs.idUser, isActive)

        activity?.let {
            viewModel.listOfSchedule?.observe(it, Observer<MutableList<Schedule>> { schedules ->
                progressBar?.visibility = View.GONE
                fab_add?.show()

                if (schedules.size > 0) {
                    adapter.schedules = schedules
                    tv_message?.visibility = View.GONE
                    recyclerView?.visibility = View.VISIBLE
                } else {
                    tv_message?.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun setupRecyclerView() {
        if (Logger.DEBUG) Log.d(TAG, "setupRecyclerView")

        val llm = LinearLayoutManager(context)
        recyclerView?.layoutManager = llm
        recyclerView?.adapter = adapter
    }

}
