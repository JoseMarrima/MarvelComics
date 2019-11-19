package com.josemarrima.marvelcomics.listOfComics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josemarrima.marvelcomics.data.ComicRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfComicsViewModel @Inject constructor(private val comicRepository: ComicRepository) : ViewModel() {

    val comics = comicRepository.getListOfComics()

    init {
        cacheData()
    }

    private fun cacheData() {
        viewModelScope.launch {
            comicRepository.refreshVideos()
        }
    }

}
