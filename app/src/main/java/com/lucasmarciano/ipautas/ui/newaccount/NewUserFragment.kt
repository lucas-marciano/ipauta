package com.lucasmarciano.ipautas.ui.newaccount

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.new_user_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewUserFragment : Fragment() {
    val TAG = Logger.tag
    private val viewModel: NewUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.new_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        setupToolbar()

        bt_create_user.setOnClickListener {
            if (checkPoliciesFields()) {
                viewModel.save(
                    User(
                        name = et_title.text.toString(),
                        email = et_mini_description.text.toString(),
                        password = et_description.text.toString()
                    ), ::responseCreatedUser
                )

            }
        }
    }

    /**
     * Method that configure a back button.
     */
    private fun setupToolbar() {
        if (Logger.DEBUG) Log.d(TAG, "setupToolbar")

        toolbar.setNavigationIcon(R.drawable.ic_back)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_newUserFragment_to_loginFragment)
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
            view?.findNavController()?.navigate(R.id.action_newUserFragment_to_loginFragment)
            Toast.makeText(
                context,
                getString(R.string.message_success_create_user),
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

    /**
     * Method to check if the all fields are correctly filed.
     */
    private fun checkPoliciesFields(): Boolean {
        if (Logger.DEBUG) Log.d(TAG, "checkPoliciesFields")

        var checkName = true
        var checkEmail = true
        var checkPassword = true
        var checkRetryPassword = true

        if (et_title.text?.isEmpty()!!) {
            checkName = false
            ti_title.error = resources.getString(R.string.message_error_name_required)
        } else {
            ti_title.isErrorEnabled = false
        }

        if (et_mini_description.text?.isEmpty()!!) {
            checkEmail = false
            ti_mini_description.error = resources.getString(R.string.message_error_email_required)
        } else {
            ti_mini_description.isErrorEnabled = false
        }

        if (et_description.text?.isEmpty()!!) {
            checkPassword = false
            ti_description.error = resources.getString(R.string.message_error_pass_required)
        } else {
            ti_description.isErrorEnabled = false
        }

        if (et_retry_password.text?.isEmpty()!!) {
            checkRetryPassword = false
            ti_retry_password.error = resources.getString(R.string.message_error_field_required)
        } else {
            ti_retry_password.isErrorEnabled = false
        }

        if (et_retry_password.text.toString() != et_description.text.toString()) {
            checkRetryPassword = false
            ti_retry_password.error = resources.getString(R.string.message_error_pass_retry)
        } else {
            ti_retry_password.isErrorEnabled = false
        }

        return checkName && checkEmail && checkPassword && checkRetryPassword
    }
}
