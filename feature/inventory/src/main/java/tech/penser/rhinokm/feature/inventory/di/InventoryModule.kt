package tech.penser.rhinokm.feature.inventory.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import tech.penser.rhinokm.feature.inventory.data.local.AppDatabase
import tech.penser.rhinokm.feature.inventory.data.repository.InventoryRepositoryImpl
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel

val inventoryModule = module {
    // Provide singleton instance of AppDatabase
    single {
        Room.databaseBuilder(
            androidApplication(), // gives Koin access to application context
            AppDatabase::class.java,
            "rhinokm-database" // name of Room database
        ).build()
    }

    single { get<AppDatabase>().storageLocationDao() }
    single { get<AppDatabase>().measurementUnitDao() }

    singleOf(::InventoryRepositoryImpl) { bind<InventoryRepository>() }
    viewModel { StorageLocationsViewModel(repository = get()) }
}