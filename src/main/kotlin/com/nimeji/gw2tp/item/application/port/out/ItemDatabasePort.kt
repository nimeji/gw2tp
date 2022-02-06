package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.domain.Item

interface ItemDatabasePort {
    fun importItem(item: Item)
    fun importItems(items: Iterable<Item>)
    fun loadItem(itemId: Item.ItemId): Item?
    fun loadAllItems(): List<Item>
    fun deleteAllItems()
}