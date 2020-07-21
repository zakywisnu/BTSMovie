package com.zeroemotion.btsmovie.ui.detail.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.data.model.Review
import com.zeroemotion.btsmovie.databinding.ItemReviewBinding

class ReviewAdapter(private val reviewList: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){

    fun updateReview(newList: List<Review>){
        reviewList.clear()
        reviewList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemReviewBinding>(
            inflater,
            R.layout.item_review,
            parent,
            false
        )
        return ReviewViewHolder(view)
    }

    override fun getItemCount() = reviewList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
//        holder.view.review = reviewList[position]
        holder.bind(reviewList[position])
    }

    class ReviewViewHolder(var view: ItemReviewBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(review: Review?){
            view.tvAuthor.text = review?.author
            view.tvContent.text = review?.content
        }
    }
}