package com.lucasmarciano.ipautas.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmarciano.ipautas.R
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

}
