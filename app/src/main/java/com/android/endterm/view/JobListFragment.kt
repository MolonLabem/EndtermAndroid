package com.android.developerjobs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.developerjobs.R
import com.android.developerjobs.adapter.PostAdapter
import com.android.developerjobs.model.Post
import com.android.developerjobs.repository.JobRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_job_list.*

class JobListFragment : Fragment() {

    private var jobAdapter: PostAdapter? = null
    private val jobsList: MutableList<Post> = mutableListOf()
    private val repository = JobRepositoryProvider.provideJobRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_job_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        repository.getJobs()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    jobAdapter?.addItems(it)
                    jobsList.addAll(it)
                },
                {
                    // TODO handle error
                }
            )
    }

    private fun initAdapter() {
        jobAdapter = PostAdapter(
            jobClickListener = {
                goToDetails(it)
            }
        )
        val manager = LinearLayoutManager(context)
        jobsRecyclerView.apply {
            layoutManager = manager
            adapter = jobAdapter
        }
    }

    private fun goToDetailsUsingNavigation(post: Post) {
        // TODO handle navigation component
    }

    private fun goToDetails(post: Post) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.mainActivity, DetailedInfo.create(post))
            ?.apply { addToBackStack(this::class.java.simpleName) }
            ?.commit()
    }
}
