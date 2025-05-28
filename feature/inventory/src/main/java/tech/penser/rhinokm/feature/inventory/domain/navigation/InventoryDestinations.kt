package tech.penser.rhinokm.feature.inventory.domain.navigation

sealed class InventoryDestination(val route: String) {
    object Landing : InventoryDestination("inventory_landing")
    object Items : InventoryDestination("inventory_items")
    object Storage : InventoryDestination("inventory_storage")
    object Units : InventoryDestination("inventory_units")
}