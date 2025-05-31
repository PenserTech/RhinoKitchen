package tech.penser.rhinokm.di

import tech.penser.rhinokm.feature.inventory.di.inventoryModule

object AppModules {
    val allModules = listOf(
        inventoryModule,
    )
}