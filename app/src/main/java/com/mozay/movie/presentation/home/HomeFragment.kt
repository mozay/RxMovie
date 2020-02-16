package com.mozay.movie.presentation.home

import android.os.Bundle
import android.view.View
import com.mozay.movie.R
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.presentation.MoviesActivity
import com.mozay.movie.presentation.base.extensions.horizontalLayout
import com.mozay.movie.presentation.base.extensions.transact
import com.mozay.movie.presentation.base.livedata.observeApi
import com.mozay.movie.presentation.base.ui.BaseFragment
import com.mozay.movie.presentation.detail.DetailFragment
import com.mozay.movie.util.ui.MovieLayers
import com.mozay.movie.util.ui.States
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel>() {

    override fun getModelClass() = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getNowPlayingMovies()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.popularMoviesLiveData.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    listPopularMovies.adapter = Adapter(it.data.moviesResult!!, object : Adapter.OnItemClickListener{
                        override fun onItemClick(movieId: Int, movieTempPoster: String) {
                            navigateDetail(movieId, movieTempPoster)
                        }
                    })
                    visibilityByDataState(States.SUCCESS, MovieLayers.POPULAR)
                }
                is DataHolder.Loading -> visibilityByDataState(States.LOADING, MovieLayers.POPULAR)
                else -> visibilityByDataState(States.FAIL, MovieLayers.POPULAR)
            }
        }

        viewModel.topRatedMoviesLiveData.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    listTopRatedMovies.adapter = Adapter(it.data.moviesResult!!, object : Adapter.OnItemClickListener{
                        override fun onItemClick(movieId: Int, movieTempPoster: String) {
                            navigateDetail(movieId, movieTempPoster)
                        }
                    })
                    visibilityByDataState(States.SUCCESS, MovieLayers.TOPRATED)
                }
                is DataHolder.Loading -> visibilityByDataState(States.LOADING, MovieLayers.TOPRATED)
                else -> visibilityByDataState(States.FAIL, MovieLayers.TOPRATED)
            }
        }

        viewModel.nowPlayingMoviesLiveData.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    listNowPlayingMovies.adapter = Adapter(it.data.moviesResult!!, object : Adapter.OnItemClickListener{
                        override fun onItemClick(movieId: Int, movieTempPoster: String) {
                            navigateDetail(movieId, movieTempPoster)
                        }
                    })
                    visibilityByDataState(States.SUCCESS, MovieLayers.NOWPLAYING)
                }
                is DataHolder.Loading -> visibilityByDataState(States.LOADING, MovieLayers.NOWPLAYING)
                else -> visibilityByDataState(States.FAIL, MovieLayers.NOWPLAYING)
            }
        }
    }

    override fun initView() {
        listPopularMovies.horizontalLayout(context)
        listTopRatedMovies.horizontalLayout(context)
        listNowPlayingMovies.horizontalLayout(context)
    }

    private fun navigateDetail(movieId: Int, movieTempPoster : String){
       if(activity is MoviesActivity){
           (activity as MoviesActivity).supportFragmentManager.transact {
               replace(
                   R.id.frameContainer,
                   DetailFragment.newInstance(movieId, movieTempPoster)
               ).addToBackStack(DetailFragment.TAG_FRAGMENT)
           }
       }
    }

    private fun visibilityByDataState(
        dataState : States,
        movieLayer : MovieLayers
    ){
        when(movieLayer){
            MovieLayers.POPULAR -> {
                when(dataState) {
                    States.LOADING -> {
                        prg1.visibility = View.VISIBLE
                        listPopularMovies.visibility = View.GONE
                        tvError1.visibility = View.GONE
                    }
                    States.SUCCESS -> {
                        prg1.visibility = View.GONE
                        listPopularMovies.visibility = View.VISIBLE
                        tvError1.visibility = View.GONE
                    }
                    States.FAIL -> {
                        tvError1.visibility = View.VISIBLE
                        prg1.visibility = View.GONE
                        listPopularMovies.visibility = View.GONE
                    }
                }
            }
            MovieLayers.TOPRATED -> {
                when(dataState) {
                    States.LOADING -> {
                        prg2.visibility = View.VISIBLE
                        listTopRatedMovies.visibility = View.GONE
                        tvError2.visibility = View.GONE
                    }
                    States.SUCCESS -> {
                        prg2.visibility = View.GONE
                        listTopRatedMovies.visibility = View.VISIBLE
                        tvError2.visibility = View.GONE
                    }
                    States.FAIL -> {
                        tvError2.visibility = View.VISIBLE
                        prg2.visibility = View.GONE
                        listTopRatedMovies.visibility = View.GONE
                    }
                }
            }
            MovieLayers.NOWPLAYING -> {
                when(dataState) {
                    States.LOADING -> {
                        prg3.visibility = View.VISIBLE
                        listNowPlayingMovies.visibility = View.GONE
                        tvError3.visibility = View.GONE
                    }
                    States.SUCCESS -> {
                        prg3.visibility = View.GONE
                        listNowPlayingMovies.visibility = View.VISIBLE
                        tvError3.visibility = View.GONE
                    }
                    States.FAIL -> {
                        tvError3.visibility = View.VISIBLE
                        prg3.visibility = View.GONE
                        listNowPlayingMovies.visibility = View.GONE
                    }
                }
            }
        }
    }

    companion object{
        fun newInstance() = HomeFragment()
    }

}