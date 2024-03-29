package com.lucasmarciano.ipautas.ui.newschedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.injection.prefs
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.new_schedule_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.text.Editable
import android.text.TextWatcher

class NewScheduleFragment : Fragment() {

    val TAG = Logger.tag
    private val viewModel: NewScheduleViewModel by viewModel()

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            checkEmptyFields()
        }

        override fun afterTextChanged(editable: Editable) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.new_schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        setupToolbar()
        tv_author_name.text = prefs.userName

        setupWatchers()

        bt_create_schedule.setOnClickListener {
            if (checkPoliciesFields()) {
                viewModel.save(
                    Schedule(
                        title = et_title.text.toString(),
                        miniDescription = et_mini_description.text.toString(),
                        description = et_description.text.toString(),
                        author = prefs.idUser
                    ), ::responseCreated
                )
            }
        }
    }


    /**
     * Method to initialize the watchers for the fields.
     */
    private fun setupWatchers() {
        et_title.addTextChangedListener(watcher)
        et_mini_description.addTextChangedListener(watcher)
        et_description.addTextChangedListener(watcher)
    }

    private fun checkEmptyFields() {
        if (et_title.text.toString() == "" || et_mini_description.text.toString() == "" || et_description.text.toString() == "") {
            bt_create_schedule.isEnabled = false
            bt_create_schedule.alpha = 0.5f
        } else {
            bt_create_schedule.isEnabled = true
            bt_create_schedule.alpha = 1f
        }
    }

    /**
     * Method that configure a back button.
     */
    private fun setupToolbar() {
        if (Logger.DEBUG) Log.d(TAG, "setupToolbar")

        toolbar.setNavigationIcon(R.drawable.ic_back)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_newScheduleFragment_to_listFragment)
        }
    }

    /**
     * Method that get the response of the database and show the results.
     *
     * @param response Boolean
     */
    private fun responseCreated(response: Boolean) {
        if (Logger.DEBUG) Log.d(TAG, "responseCreated")

        if (response) {
            view?.findNavController()?.navigate(R.id.action_newScheduleFragment_to_listFragment)
            Toast.makeText(
                context,
                getString(R.string.message_success_create_schedule),
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

        var checkTitle = true
        var checkMiniDescription = true
        var checkDescription = true

        if (et_title.text?.isEmpty()!!) {
            checkTitle = false
            ti_title.error = resources.getString(R.string.message_error_field_required)
        } else {
            ti_title.isErrorEnabled = false
        }

        if (et_mini_description.text?.isEmpty()!!) {
            checkMiniDescription = false
            ti_mini_description.error = resources.getString(R.string.message_error_field_required)
        } else {
            ti_mini_description.isErrorEnabled = false
        }

        if (et_description.text?.isEmpty()!!) {
            checkDescription = false
            ti_description.error = resources.getString(R.string.message_error_field_required)
        } else {
            ti_description.isErrorEnabled = false
        }

        return checkTitle && checkMiniDescription && checkDescription
    }

}
