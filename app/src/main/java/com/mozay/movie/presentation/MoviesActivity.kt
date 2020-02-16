package com.mozay.movie.presentation

import android.os.Bundle
import com.mozay.movie.presentation.base.extensions.transact
import com.mozay.movie.presentation.base.ui.BaseActivity
import com.mozay.movie.presentation.home.HomeFragment
import com.mozay.movie.R


class MoviesActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.layout_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.transact {
            replace(
                R.id.frameContainer,
                HomeFragment.newInstance()
            )
        }
    }
}