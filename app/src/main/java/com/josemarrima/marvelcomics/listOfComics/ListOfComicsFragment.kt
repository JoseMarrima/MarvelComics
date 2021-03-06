package com.josemarrima.marvelcomics.listOfComics

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.josemarrima.marvelcomics.R
import com.josemarrima.marvelcomics.databinding.ListOfComicsFragmentBinding
import com.josemarrima.marvelcomics.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class ListOfComicsFragment : DaggerFragment() {

    private lateinit var viewModel: ListOfComicsViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val adapter = ListOfComicsAdapter(ClickListener {
        this.findNavController().navigate(ListOfComicsFragmentDirections
            .actionListOfComicsFragmentToComicDetailsFragment(it))
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListOfComicsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_of_comics_fragment,
            container,
            false)

        binding.lifecycleOwner = this

        binding.listComicsRv.adapter = adapter

        viewModel = ViewModelProviders.of(this, factory).get(ListOfComicsViewModel::class.java)

        viewModel.comics.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

}
