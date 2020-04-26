package com.dorokhov.soundmix.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.*
import coil.api.load
import com.dorokhov.soundmix.R
import com.dorokhov.soundmix.models.SoundModel
import kotlinx.android.synthetic.main.item_audio.view.*

class SoundRecyclerViewAdapter(
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SoundModel>() {

        override fun areItemsTheSame(oldItem: SoundModel, newItem: SoundModel): Boolean {
            return oldItem.soundId == newItem.soundId
        }

        override fun areContentsTheSame(oldItem: SoundModel, newItem: SoundModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(
        BlogRecyclerChangeCallBack(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )

    internal inner class BlogRecyclerChangeCallBack(
        private val adapter: SoundRecyclerViewAdapter
    ) : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyItemRangeRemoved(position, count)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SoundViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_audio,
                parent,
                false
            ),
            interaction = interaction
        )
    }

    fun submitList(list: List<SoundModel>?) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SoundViewHolder) {
            holder.bind(differ.currentList[position])
        }
    }

    class SoundViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SoundModel) = with(itemView) {
            sound_ImageView.load(item.image) {
                crossfade(true)
            }

            volume_seekBar.progress = item.volumeLevel
            volume_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    interaction?.changeVolume(adapterPosition, item, seekBar?.progress!!)
                }
            })

            name_TextView.text = item.name
            play_ImageButton.setOnClickListener {
                interaction?.playSound(adapterPosition, item)
            }

            stop_ImageButton.setOnClickListener {
                interaction?.stopSound(adapterPosition, item)
            }
        }
    }

    interface Interaction {
        fun changeVolume(position: Int, item: SoundModel, newVolume: Int)

        fun playSound(position: Int, item: SoundModel)

        fun stopSound(position: Int, item: SoundModel)
    }
}