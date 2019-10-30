package com.lucasmarciano.ipautas.ui.newschedule

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lucasmarciano.ipautas.R

class NewScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = NewScheduleFragment()
    }

    private lateinit var viewModel: NewScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
