package io.luxus.adofai.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.luxus.adofai.databinding.ItemLevelBinding
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.animation.presentation.view.custom.adapter.RecyclerViewAdapter

class LevelListAdapter(
    private val modelList: List<CustomLevel>
) : RecyclerViewAdapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return LevelViewHolder(ItemLevelBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: CustomLevel = modelList[position]
        (holder as LevelViewHolder).bind(model)
    }

    override fun getItemId(position: Int): Long =
        modelList[position].hashCode().toLong()

    override fun getItemCount(): Int =
        modelList.size

    class LevelViewHolder(private val binding: ItemLevelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CustomLevel) {
            binding.model = model
        }
    }

}