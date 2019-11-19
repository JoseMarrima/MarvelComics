package com.josemarrima.marvelcomics.listOfComics

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.josemarrima.marvelcomics.R
import com.josemarrima.marvelcomics.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class ListOfComicsFragment : DaggerFragment() {

    private lateinit var viewModel: ListOfComicsViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, factory).get(ListOfComicsViewModel::class.java)

        viewModel.comics.observe(viewLifecycleOwner, Observer {
            Timber.d("List of comics $it")
        })

        return inflater.inflate(R.layout.list_of_comics_fragment, container, false)
    }

}
