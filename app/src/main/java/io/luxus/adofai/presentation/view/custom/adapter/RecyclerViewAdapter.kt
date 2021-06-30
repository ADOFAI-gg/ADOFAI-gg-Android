package io.luxus.adofai.presentation.view.custom.adapter

import androidx.recyclerview.widget.RecyclerView
import io.luxus.adofai.presentation.view.custom.type.collection.ListenableList

abstract class RecyclerViewAdapter<T: RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    val listener = object: ListenableList.Listener {

        override fun onItemChanged(position: Int) {
            notifyItemChanged(position)
        }

        override fun onInserted(positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onRemoved(positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onAllChanged() {
            // TODO : Use DiffUtil
            notifyDataSetChanged()
        }

    }

}