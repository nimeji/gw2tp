package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto

interface ItemListingsDataSourcePort {
    fun availableItemListingsIds(): Set<Int>
    fun retrieveAllItemListings(): Sequence<ItemListingsDto>
}