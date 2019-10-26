package com.lucasmarciano.ipautas.ui.newaccount

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lucasmarciano.ipautas.R

class NewUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewUserFragment()
    }

    private lateinit var viewModel: NewUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
