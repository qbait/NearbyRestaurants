package eu.szwiec.mapssample.di

import eu.szwiec.mapssample.db.CoordinatesDao
import eu.szwiec.mapssample.db.CoordinatesDb
import org.koin.dsl.module.module

val dbModule = module {
    single { CoordinatesDb.getInstance(get()) }
    single { createDao(get()) }
}

fun createDao(db: CoordinatesDb): CoordinatesDao {
    return db.dao()
}
