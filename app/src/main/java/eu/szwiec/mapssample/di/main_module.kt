package eu.szwiec.mapssample.di

import eu.szwiec.mapssample.location.BoundLocationManager
import eu.szwiec.mapssample.repository.Repository
import eu.szwiec.mapssample.repository.RepositoryImpl
import eu.szwiec.mapssample.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
    single { RepositoryImpl(get(), get(), get()) as Repository }
    single { BoundLocationManager.getInstance(get()) }
}