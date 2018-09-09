package eu.szwiec.mapssample.di

import eu.szwiec.checkittravelkit.repository.local.CoordinatesDao
import eu.szwiec.checkittravelkit.repository.local.CoordinatesDb
import org.koin.dsl.module.module

val dbModule = module {
    single { CoordinatesDb.getInstance(get()) }
    single { createDao(get()) }
}

fun createDao(db: CoordinatesDb): CoordinatesDao {
    return db.dao()
}
