package com.lucasmarciano.ipautas.ui.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmarciano.ipautas.R
import org.koin.android.viewmodel.ext.android.viewModel

class RecoverFragment : Fragment() {

    val viewModel: RecoverViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recover_fragment, container, false)
    }

}
