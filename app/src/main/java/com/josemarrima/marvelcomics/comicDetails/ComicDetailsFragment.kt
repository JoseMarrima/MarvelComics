package com.josemarrima.marvelcomics.comicDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.josemarrima.marvelcomics.R
import com.josemarrima.marvelcomics.databinding.ComicDetailsFragmentBinding
import com.josemarrima.marvelcomics.databinding.ListOfComicsFragmentBinding
import com.josemarrima.marvelcomics.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ComicDetailsFragment : DaggerFragment() {

    private lateinit var viewModel: ComicDetailsViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ComicDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.comic_details_fragment,
            container,
            false)

        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, factory).get(ComicDetailsViewModel::class.java)

        val args = ComicDetailsFragmentArgs.fromBundle(arguments!!)

        binding.comic = args.comic

        return binding.root
    }


}
