package com.android.developerjobs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.android.developerjobs.R
import com.android.developerjobs.model.Post
import kotlinx.android.synthetic.main.fragment_detailed_info.*


class DetailedInfo() : Fragment() {

    companion object {
        const val ARG_POST = "post"

        fun create(post: Post) = DetailedInfo().apply {
            arguments = bundleOf(ARG_POST to post)
        }
    }

    private val post: Post by lazy {
        arguments?.getParcelable<Post>(ARG_POST) as Post
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detailed_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        commentsButton.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.mainActivity, CommentsFragment.create(post.id))
                ?.apply { addToBackStack(this::class.java.simpleName) }
                ?.commit()
        }
    }

    private fun initView() {
        postTitle.text = post.title
        postBody.text = post.body
    }
}
