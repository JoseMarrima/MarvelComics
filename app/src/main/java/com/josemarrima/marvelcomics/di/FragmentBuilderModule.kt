package com.josemarrima.marvelcomics.di

import com.josemarrima.marvelcomics.comicDetails.ComicDetailsFragment
import com.josemarrima.marvelcomics.di.comicDetails.ComicDetailsViewModelModule
import com.josemarrima.marvelcomics.di.listOfComics.ListOfComicsViewModelModule
import com.josemarrima.marvelcomics.listOfComics.ListOfComicsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ListOfComicsViewModelModule::class])
    abstract fun contributeListOfComicsFragment() : ListOfComicsFragment

    @ContributesAndroidInjector(modules = [ComicDetailsViewModelModule::class])
    abstract fun contributeComicDetailsFragment() : ComicDetailsFragment
}