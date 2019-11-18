package com.josemarrima.marvelcomics.comicDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.josemarrima.marvelcomics.R
import dagger.android.support.DaggerFragment

class ComicDetailsFragment : DaggerFragment() {

    private lateinit var viewModel: ComicDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comic_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ComicDetailsViewModel::class.java)

    }

}
