package com.josemarrima.marvelcomics.di

import android.content.Context
import com.josemarrima.marvelcomics.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    ViewModelFactoryModule::class,
    FragmentBuilderModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}