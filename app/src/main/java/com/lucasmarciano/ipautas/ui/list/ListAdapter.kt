package com.lucasmarciano.ipautas.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lucasmarciano.ipautas.R
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.injection.prefs
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Adapter for the list of the schedules.
 *
 * @project iPautas
 * @create_at 2019-10-29
 * @author lucasmarciano
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val TAG = Logger.tag

    var schedules: MutableList<Schedule> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (Logger.DEBUG) Log.d(TAG, "onCreateViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(schedules[position])

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val TAG = Logger.tag

        fun bind(schedule: Schedule) {
            if (Logger.DEBUG) Log.d(TAG, "bind")

            itemView.tv_title.text = schedule.title
            itemView.tv_description.text = schedule.miniDescription
            itemView.tv_author.text = prefs.userName
            itemView.tv_created_at.text = schedule.createdAt

            itemView.setOnClickListener {
                val bundle = bundleOf("scheduleId" to schedule.id)
                itemView.findNavController()
                    .navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }
}