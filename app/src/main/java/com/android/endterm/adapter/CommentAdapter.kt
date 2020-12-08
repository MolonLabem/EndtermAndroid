package com.android.developerjobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.developerjobs.R
import com.android.developerjobs.model.Comment
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.item.view.*

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobList: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount() = jobList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(jobList[position])
    }

    fun addItems(list: List<Comment>) {
        jobList.clear()
        jobList.addAll(list)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.comment_item, parent, false)) {

        private val name = itemView.commentName
        private val email = itemView.commentEmail
        private val body = itemView.commentBody

        fun bind(comment: Comment) {
            name.text = comment.name
            email.text = comment.email
            body.text = comment.body
        }
    }
}