package tech.penser.rhinokm.feature.inventory.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel

val inventoryModule = module {
    viewModel { StorageLocationsViewModel() }
}