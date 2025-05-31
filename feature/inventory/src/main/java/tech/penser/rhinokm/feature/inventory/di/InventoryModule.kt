package tech.penser.rhinokm.feature.inventory.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationViewModel

val inventoryModule = module {
    viewModel { StorageLocationViewModel() }
}