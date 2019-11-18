package com.josemarrima.marvelcomics.listOfComics

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.josemarrima.marvelcomics.R

class ListOfComics : Fragment() {

    companion object {
        fun newInstance() = ListOfComics()
    }

    private lateinit var viewModel: ListOfComicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_of_comics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListOfComicsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
