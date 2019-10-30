package com.lucasmarciano.ipautas.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
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
import android.view.MenuInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.perfil_fragment.*

class ListFragment : Fragment() {
    val TAG = Logger.tag

    private val adapter: ListAdapter by inject()
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        setupRecyclerView()
        loadData()

        bt_logout.setOnClickListener {
            prefs.clearPrefs()
            it.findNavController().navigate(R.id.action_listFragment_to_loginFragment)
            Toast.makeText(context, getString(R.string.message_logout), Toast.LENGTH_LONG).show()
        }

        bnv_lists.setOnNavigationItemSelectedListener { item ->
            progressBar?.visibility = View.VISIBLE
            recyclerView?.visibility = View.GONE
            tv_message?.visibility = View.GONE
            fab_add?.hide()

            when (item.itemId) {
                R.id.navigation_list_active -> loadData()
                R.id.navigation_list_inactive -> loadData(false)
                R.id.navigation_perfil -> showPerfil()
                else -> loadData()
            }

            true
        }

        fab_add.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_newScheduleFragment)
        }
    }

    /**
     * Method to show a perfil screen
     */
    private fun showPerfil() {
        progressBar?.visibility = View.GONE
        recyclerView?.visibility = View.GONE
        tv_message?.visibility = View.GONE
        fab_add?.hide()

        ic_perfil.visibility = View.VISIBLE
        tv_name_user.text = prefs.userName
        tv_email_user.text = prefs.userEmail
    }

    /**
     * Method that get data in data base.
     *
     * @param isActive Boolean
     */
    private fun loadData(isActive: Boolean = true) {
        if (Logger.DEBUG) Log.d(TAG, "loadDataByActive")
        ic_perfil.visibility = View.GONE

        viewModel.getListSchedules(prefs.idUser, isActive)

        activity?.let {
            viewModel.listOfSchedule?.observe(it, Observer<MutableList<Schedule>> { schedules ->
                progressBar?.visibility = View.GONE
                fab_add?.show()

                if (schedules.size > 0) {
                    adapter.schedules = schedules
                    adapter.notifyDataSetChanged()
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
