package com.nimeji.gw2tp.item.adapter.out.persistence

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.application.port.out.LoadItemPort
import com.nimeji.gw2tp.item.application.port.out.UpsertItemPort
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ItemPersistenceAdapter (
    @Autowired private val itemRepository: ItemRepository,
): LoadItemPort, UpsertItemPort {
    override fun loadItem(itemId: Item.ItemId): Result<Item, ItemError> {
        val item = itemRepository.findById(itemId.value).orElse(null)
            ?: return Err(ItemError.ITEM_DOES_NOT_EXIST)

        return Ok(item.toDomain())
    }

    override fun loadAllItems(): List<Item> {
        return itemRepository.findAll().map { item -> item.toDomain() }
    }

    override fun upsertItem(item: Item): Result<Unit, ItemError> {
        itemRepository.save(ItemEntity(item))

        return Ok(Unit)
    }
}