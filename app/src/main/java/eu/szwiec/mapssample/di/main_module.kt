package eu.szwiec.mapssample.di

import eu.szwiec.mapssample.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel { MainViewModel(get(), get()) }
}