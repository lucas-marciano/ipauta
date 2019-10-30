package com.lucasmarciano.ipautas.ui.recover

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.recover_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class RecoverFragment : Fragment() {
    val TAG = Logger.tag
    val viewModel: RecoverViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.recover_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        setupToolbar()

        bt_recover_pass.setOnClickListener {
            if (checkPoliciesFields()) {
                // Call the viewModel to send a e-mail

                Toast.makeText(
                    context,
                    getString(R.string.message_success_recover_pass),
                    Toast.LENGTH_LONG
                ).show()
                it.findNavController().navigate(R.id.action_recoverFragment_to_loginFragment)
            }
        }
    }

    /**
     * Method that configure a back button.
     */
    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_recoverFragment_to_loginFragment)
        }
    }

    /**
     * Method to check if the all fields are correctly filed.
     *
     * @return Boolean
     */
    private fun checkPoliciesFields(): Boolean {
        if (Logger.DEBUG) Log.d(TAG, "checkPoliciesFields")

        return if (et_mini_description.text?.isEmpty()!!) {
            ti_mini_description.error = resources.getString(R.string.message_error_email_required)
            false
        } else {
            ti_mini_description.isErrorEnabled = false
            true
        }
    }

}
