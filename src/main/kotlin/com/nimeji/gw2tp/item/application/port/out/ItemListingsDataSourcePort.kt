package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto

interface ItemListingsDataSourcePort {
    fun retrieveItemListingsForItemIds(ids: Set<Int>): Iterable<ItemListingsDto>
    fun availableItemListingsIds(): Set<Int>
}