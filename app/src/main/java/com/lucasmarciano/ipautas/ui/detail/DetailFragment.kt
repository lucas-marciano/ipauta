package com.lucasmarciano.ipautas.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.injection.prefs
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val TAG = Logger.tag
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        val scheduleId = arguments?.getLong("scheduleId")
        setupToolbar()
        scheduleId?.let { id ->
            loadData(id)
        }
    }

    /**
     * Method that configure a back button.
     */
    private fun setupToolbar() {
        if (Logger.DEBUG) Log.d(TAG, "setupToolbar")

        toolbar.setNavigationIcon(R.drawable.ic_back)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }
    }

    /**
     * Methodo that get data in data base.
     */
    private fun loadData(scheduleId: Long) {
        viewModel.getSchedule(scheduleId)

        activity?.let {
            viewModel.mSchedule?.observe(it, Observer<Schedule> { schedule ->
                updateInterface(schedule)
            })
        }
    }

    /**
     * Method that update the data information.
     *
     * @param schedule Schedule
     */
    private fun updateInterface(schedule: Schedule) {
        if (Logger.DEBUG) Log.d(TAG, "updateInterface")

        tv_author?.text = prefs.userName
        tv_description?.text = schedule.description
        tv_title?.text = schedule.title

        sw_active?.isChecked = schedule.isActive

        sw_active?.text = if (schedule.isActive) {
            getString(R.string.label_close)
        } else {
            getString(R.string.label_reopen)
        }

        sw_active?.setOnCheckedChangeListener { _, value ->
            schedule.isActive = value
            viewModel.updateSchedule(schedule, ::responseCreatedUser)
        }
    }

    /**
     * Method that get the response of the database and show the results.
     *
     * @param response Boolean
     */
    private fun responseCreatedUser(response: Boolean) {
        if (Logger.DEBUG) Log.d(TAG, "responseCreatedUser")

        if (response) {
            Toast.makeText(
                context,
                getString(R.string.message_success_update_schedule),
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                context,
                getString(R.string.message_error_create),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
