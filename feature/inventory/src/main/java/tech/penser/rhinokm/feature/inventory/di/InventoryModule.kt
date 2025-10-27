package tech.penser.rhinokm.feature.inventory.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import tech.penser.rhinokm.feature.inventory.data.repository.InventoryRepositoryImpl
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel

val inventoryModule = module {
    singleOf(::InventoryRepositoryImpl) { bind<InventoryRepository>() }
    viewModel { StorageLocationsViewModel(repository = get()) }
}