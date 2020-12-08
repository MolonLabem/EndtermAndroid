package com.android.developerjobs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.developerjobs.R
import com.android.developerjobs.adapter.CommentAdapter
import com.android.developerjobs.repository.JobRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_detailed_info.*
import kotlinx.android.synthetic.main.fragment_job_list.*


class CommentsFragment() : Fragment() {

    companion object {
        private const val ARG_POST_ID = "postId"

        fun create(id: Int) = CommentsFragment().apply {
            arguments = bundleOf(ARG_POST_ID to id)
        }
    }

    private val repository = JobRepositoryProvider.provideJobRepository()
    private val commentsAdapter = CommentAdapter()

    private val postId: Int by lazy {
        arguments?.getInt(ARG_POST_ID) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_comments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        repository.getComments(postId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    commentsAdapter.addItems(it)
                },
                {
                    // TODO handle error
                }
            )
    }

    private fun initView() {
        val manager = LinearLayoutManager(context)
        commentsRecyclerView.apply {
            layoutManager = manager
            adapter = commentsAdapter
        }
    }
}
