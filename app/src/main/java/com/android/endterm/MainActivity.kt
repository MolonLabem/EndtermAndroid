package com.android.developerjobs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.developerjobs.view.JobListFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val jobListFragment = JobListFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainActivity, jobListFragment)
        fragmentTransaction.commit()
    }
}
