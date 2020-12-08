package com.android.developerjobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.developerjobs.R
import com.android.developerjobs.model.Post
import kotlinx.android.synthetic.main.item.view.*

class PostAdapter(private val jobClickListener: (post: Post) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobList: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount() = jobList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(jobList[position], jobClickListener)
    }

    fun addItems(list: List<Post>) {
        jobList.clear()
        jobList.addAll(list)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {

        private val title = itemView.postTitle
        private val postLayout = itemView.postLayout

        fun bind(post: Post, jobClickListener: (post: Post) -> Unit) {
            title.text = post.title
            postLayout.setOnClickListener {
                jobClickListener(post)
            }
        }
    }
}