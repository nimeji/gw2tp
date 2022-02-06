package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.domain.Item

interface ItemDataSourcePort {
    fun retrieveAllItems(): Iterable<Item>
    fun retrieveItemsById(ids: List<Item.ItemId>): Iterable<Item>
}