package com.zeroemotion.btsmovie.ui.detail.trailer

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.data.model.Trailer
import com.zeroemotion.btsmovie.databinding.ItemTrailerBinding

class TrailerAdapter(private val trailerList: ArrayList<Trailer>) :
    RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {


    fun updateTrailer(newList: List<Trailer>) {
        trailerList.clear()
        trailerList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTrailerBinding>(
            inflater,
            R.layout.item_trailer,
            parent,
            false
        )
        return TrailerViewHolder(
            view
        )
    }

    override fun getItemCount() = trailerList.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bindTo(trailerList[position])
    }

    class TrailerViewHolder(var view: ItemTrailerBinding) : RecyclerView.ViewHolder(view.root) {
        fun bindTo(trailer: Trailer?) {
            val thumbnail = "https://img.youtube.com/vi/" + trailer?.key + "/hqdefault.jpg"
            Glide.with(itemView.context)
                .load(thumbnail)
                .placeholder(R.color.colorDivider)
                .into(view.imageTrailer)

            view.trailerName.text = trailer?.name

            view.cardTrailer.setOnClickListener { v ->
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer?.key))
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${trailer?.key}")
                )
                if (appIntent.resolveActivity(itemView.context.packageManager) != null) {
                    itemView.context.startActivity(appIntent)
                } else {
                    itemView.context.startActivity(webIntent)
                }
            }
            view.executePendingBindings()
        }
    }


}