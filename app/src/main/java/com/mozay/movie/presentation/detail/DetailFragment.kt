package com.mozay.movie.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.mozay.movie.R
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.presentation.base.extensions.loadImage
import com.mozay.movie.presentation.base.livedata.observeApi
import com.mozay.movie.presentation.base.ui.BaseFragment
import com.mozay.movie.util.Constants
import com.mozay.movie.util.ui.MovieLayers
import com.mozay.movie.util.ui.States
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.prg1

class DetailFragment : BaseFragment<DetailViewModel>() {

    private var movieId : Int? = null
    private var movieTempPoster : String? = null

    override fun getModelClass() = DetailViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.fragment_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(BUNDLE_MOVIE_ID)
            movieTempPoster = it.getString(BUNDLE_TEMP_POSTER_URL)
        }

        movieId?.let {
            viewModel.getMovieDetail(movieId!!)
            viewModel.getMovieImages(movieId!!)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movieDetailLiveData.observeApi(this) { it ->
            when (it) {
                is DataHolder.Success -> {
                    //region fields
                    it.data.title?.let { tvMovieInfo.text = it }
                    it.data.originalTitle?.let { tvMovieOriginalTitle.text = it }
                    it.data.overview?.let { tvSummary.text = it }
                    it.data.voteAvarage?.let { tvVote.text = it.toString() }
                    it.data.releaseDate?.let { tvDate.text = it }
                    it.data.runtime?.let { tvRuntime.text = it.toString() + resources.getString(R.string.pref_min) }

                    if(it.data.genres != null && it.data.genres.isNotEmpty()){
                        var genresData : String? = ""
                        for ((i,v) in it.data.genres.withIndex()){
                            genresData += if(i == it.data.genres.size - 1){
                                v.name
                            }else{
                                v.name + resources.getString(R.string.bullet)
                            }
                        }
                        tvMovieCategories.text = genresData
                    }

                    //endregion
                    visibilityByDataState(States.SUCCESS, MovieLayers.DETAIL)
                }
                is DataHolder.Loading -> visibilityByDataState(States.LOADING, MovieLayers.DETAIL)
                else -> visibilityByDataState(States.FAIL, MovieLayers.DETAIL)
            }
        }

        viewModel.movieImagesLiveData.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    if(it.data.posters != null
                        && it.data.posters.isNotEmpty()
                        && it.data.posters.size >= 3 //efficiently solve - your algorithm
                    ){
                        imgPoster1.loadImage(this.context!!, Constants.POSTER_BASE_URL + it.data.posters[0].moviePoster)
                        imgPoster2.loadImage(this.context!!, Constants.POSTER_BASE_URL + it.data.posters[1].moviePoster)
                        imgPoster3.loadImage(this.context!!, Constants.POSTER_BASE_URL + it.data.posters[2].moviePoster)
                    }
                    visibilityByDataState(States.SUCCESS, MovieLayers.POSTERS)
                }
                is DataHolder.Loading -> visibilityByDataState(States.LOADING, MovieLayers.POSTERS)
                else -> visibilityByDataState(States.FAIL, MovieLayers.POSTERS)
            }
        }
    }


    private fun visibilityByDataState(
        dataState : States,
        movieLayer : MovieLayers
    ){
        when(movieLayer) {
            MovieLayers.POSTERS -> {
                when (dataState) {
                    States.LOADING -> {
                        prg1.visibility = View.VISIBLE
                        containerPoster.visibility = View.GONE
                        container1.visibility = View.VISIBLE
                    }
                    States.SUCCESS -> {
                        prg1.visibility = View.GONE
                        containerPoster.visibility = View.VISIBLE
                        container1.visibility = View.VISIBLE
                    }
                    States.FAIL -> {
                        prg1.visibility = View.GONE
                        containerPoster.visibility = View.GONE
                        container1.visibility = View.GONE
                    }
                }
            }
            MovieLayers.DETAIL -> {
                when (dataState) {
                    States.LOADING -> {
                        prg2.visibility = View.VISIBLE
                        container2.visibility = View.VISIBLE
                    }
                    States.SUCCESS -> {
                        prg2.visibility = View.GONE
                        container2.visibility = View.VISIBLE
                    }
                    States.FAIL -> {
                        prg2.visibility = View.GONE
                        container2.visibility = View.GONE
                    }
                }
            }
        }
    }

    companion object{
        private const val BUNDLE_MOVIE_ID = "bundle_movie_id"
        private const val BUNDLE_TEMP_POSTER_URL = "bundle_movie_temp_poster"
        const val TAG_FRAGMENT = "detail"
        fun newInstance(movieId : Int, movieTempPoster : String) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_MOVIE_ID, movieId)
                putString(BUNDLE_TEMP_POSTER_URL, movieTempPoster)
            }
        }
    }
}