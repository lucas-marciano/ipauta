package com.lucasmarciano.ipautas.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmarciano.ipautas.R
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        bt_login.setOnClickListener {
            if(checkPoliciesFields()){
                // TODO: Go to viewmodel to check if the user exist

            }
        }
    }

    /**
     * Method to check if the all fields are correctly filed.
     *
     * @return Boolean
     */
    private fun checkPoliciesFields(): Boolean{
        var checkEmail = true
        var checkPassword = true

        if (et_email.text?.isEmpty()!!){
            checkEmail = false
            ti_email.error = "O campo de E-mail é obrigatório"
        }

        if (et_password.text?.isEmpty()!!){
            checkPassword = false
            ti_password.error = "O campo de Senha é obrigatório"
        }

        return checkEmail && checkPassword
    }

}
