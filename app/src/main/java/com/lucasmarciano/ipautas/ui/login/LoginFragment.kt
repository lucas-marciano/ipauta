package com.lucasmarciano.ipautas.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.injection.prefs
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

        if (prefs.stayLogged) {
            view.findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        } else {

            bt_login.setOnClickListener {
                if (checkPoliciesFields()) {
                    viewModel.logIn(
                        et_email.text.toString(),
                        et_password.text.toString(),
                        ::responseLogin
                    )
                }
            }

            tv_new_account.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_newUserFragment)
            }

            tv_forgot_pass.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
            }
        }
    }

    /**
     * Method that get the response of the database and show the results.
     *
     * @param user User
     */
    private fun responseLogin(user: User?) {
        if (Logger.DEBUG) Log.d(TAG, "responseLogin")

        if (user != null) {
            stayLoggedConfigurations(user)
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_listFragment)
        } else {
            if (Logger.DEBUG) Log.e(TAG, "responseLogin: error")
        }
    }

    /**
     * Method that will set the configurations for the user stay logged.
     *
     * @param user User
     */
    private fun stayLoggedConfigurations(user: User) {
        if (Logger.DEBUG) Log.d(TAG, "stayLoggedConfigurations")

        cb_stay_logged.setOnCheckedChangeListener { _, changed ->
            prefs.stayLogged = changed
        }

        if (prefs.stayLogged)
            prefs.idUser = user.id
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
