package io.luxus.adofai.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.luxus.adofai.databinding.ItemLevelBinding
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.presentation.view.custom.adapter.RecyclerViewAdapter

class LevelListAdapter: RecyclerViewAdapter<LevelListAdapter.LevelViewHolder>() {

    private lateinit var modelList: List<CustomLevel>

    fun init(modelList: List<CustomLevel>) {
        this.modelList = modelList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return LevelViewHolder(ItemLevelBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val model: CustomLevel = modelList[position]
        holder.bind(model)
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