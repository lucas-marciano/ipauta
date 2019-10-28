package com.lucasmarciano.ipautas.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    val TAG = Logger.tag
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        bt_login.setOnClickListener {
            if (checkPoliciesFields()) {
                // TODO: Go to viewmodel to check if the user exist
            }
        }

        tv_new_account.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_newUserFragment)
        }

        tv_forgot_pass.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }
    }

    /**
     * Method to check if the all fields are correctly filed.
     *
     * @return Boolean
     */
    private fun checkPoliciesFields(): Boolean {
        if (Logger.DEBUG) Log.d(TAG, "checkPoliciesFields")

        var checkEmail = true
        var checkPassword = true

        if (et_email.text?.isEmpty()!!) {
            checkEmail = false
            ti_email.error = resources.getString(R.string.message_error_email_required)
        }

        if (et_password.text?.isEmpty()!!) {
            checkPassword = false
            ti_password.error = resources.getString(R.string.message_error_pass_required)
        }

        return checkEmail && checkPassword
    }

}
