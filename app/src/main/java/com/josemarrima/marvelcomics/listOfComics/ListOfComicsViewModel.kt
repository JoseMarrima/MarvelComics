package com.josemarrima.marvelcomics.listOfComics

import androidx.lifecycle.ViewModel
import com.josemarrima.marvelcomics.data.ComicRepository
import javax.inject.Inject

class ListOfComicsViewModel @Inject constructor(private val comicRepository: ComicRepository) : ViewModel() {

}
