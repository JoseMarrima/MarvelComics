package com.josemarrima.marvelcomics.di.listOfComics

import androidx.lifecycle.ViewModel
import com.josemarrima.marvelcomics.di.ViewModelKey
import com.josemarrima.marvelcomics.listOfComics.ListOfComicsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListOfComicsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListOfComicsViewModel::class)
    abstract fun bindListOfComicsViewModel(listOfComicsViewModel: ListOfComicsViewModel): ViewModel
}
