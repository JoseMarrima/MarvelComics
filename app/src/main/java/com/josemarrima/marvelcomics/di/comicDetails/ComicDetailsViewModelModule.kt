package com.josemarrima.marvelcomics.di.comicDetails

import androidx.lifecycle.ViewModel
import com.josemarrima.marvelcomics.comicDetails.ComicDetailsViewModel
import com.josemarrima.marvelcomics.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ComicDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ComicDetailsViewModel::class)
    abstract fun bindComicDetailsViewModel(comicDetailsViewModel: ComicDetailsViewModel): ViewModel

}
