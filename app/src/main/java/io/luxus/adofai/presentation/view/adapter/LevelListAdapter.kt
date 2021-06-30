package io.luxus.adofai.presentation.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.luxus.adofai.R
import io.luxus.adofai.databinding.ItemLevelBinding
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.presentation.view.custom.adapter.RecyclerViewAdapter
import kotlin.math.floor
import kotlin.math.min

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
        @SuppressLint("SetTextI18n")
        fun bind(model: CustomLevel) {
            binding.id.text = model.id.toString()
            binding.song.text = model.song
            setLevel(binding.level, model.level)
            binding.artist.text = model.artist.joinToString(" & ")
            binding.EW.visibility = if (model.epilepsyWarning) View.VISIBLE else View.GONE
            binding.creator.text = getCreatorString(model.creator)
            binding.bpm.text = getBpmString(model.minBpm, model.maxBpm)
            binding.tiles.text = getTileString(model.tiles)
            binding.tags.text = model.tags.joinToString(" ")
        }

        @SuppressLint("SetTextI18n")
        private fun setLevel(textView: TextView, level: Double) {
            val levelText = "${level.toInt()}${if (floor(level) != level) "+" else ""}"
            textView.text = levelText

            textView.setBackgroundResource(when (levelText) {
                "-2"-> R.color.level_m2
                "-1"-> R.color.level_m1
                "0"-> R.color.level_0
                "1"-> R.color.level_1
                "2"-> R.color.level_2
                "3"-> R.color.level_3
                "4"-> R.color.level_4
                "5"-> R.color.level_5
                "6"-> R.color.level_6
                "7"-> R.color.level_7
                "8"-> R.color.level_8
                "9"-> R.color.level_9
                "10"-> R.color.level_10
                "11"-> R.color.level_11
                "12"-> R.color.level_12
                "13"-> R.color.level_13
                "14"-> R.color.level_14
                "15"-> R.color.level_15
                "16"-> R.color.level_16
                "17"-> R.color.level_17
                "17+"-> R.color.level_17p
                "18"-> R.color.level_18
                "18+"-> R.color.level_18p
                "19"-> R.color.level_19
                "19+"-> R.color.level_19p
                "20"-> R.color.level_20
                "20+"-> R.color.level_20p
                else-> R.color.level_0
            })
            textView.setTextColor(ContextCompat.getColor(textView.context, when (levelText) {
                "-2", "-1", "18","18+", "19", "19+", "20"-> R.color.level_white
                "20+"-> R.color.level_red
                else-> R.color.level_black
            }))
        }

        private fun getCreatorString(creators: List<String>): String =
            "Map by ${creators.subList(0, min(3, creators.size)).joinToString(" & ")} " +
                    if (creators.size > 3) "외 ${creators.size-3}명" else ""

        private fun getBpmString(minBpm: Double?, maxBpm: Double?): String {
            if (minBpm == null || maxBpm == null) return ""
            if (minBpm == maxBpm) return "${numStr(minBpm)} BPM"
            return "${numStr(minBpm)}-${numStr(maxBpm)} BPM"
        }

        private fun getTileString(tile: Long?): String {
            if (tile == null) return ""
            return "$tile Tiles"
        }

        private fun numStr(value: Double): String {
            return if (floor(value) == value) value.toInt().toString() else value.toString()
        }

    }

}