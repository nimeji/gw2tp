package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.domain.Item

interface ItemDataSourcePort {
    fun retrieveBuildVersion(): Int
    fun retrieveItemsById(ids: Collection<Int>): Iterable<Item>
    fun availableItemIds(): Set<Int>
}