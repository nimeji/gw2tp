package com.nimeji.gw2tp.item.application.port.out

import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemError

interface LoadItemPort {
    fun loadItem(itemId: Item.ItemId): Result<Item, ItemError>
    fun loadAllItems(): List<Item>
}