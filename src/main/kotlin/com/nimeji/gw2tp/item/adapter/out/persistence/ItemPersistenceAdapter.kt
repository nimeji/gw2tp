package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import com.nimeji.gw2tp.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ItemPersistenceAdapter (
    @Autowired private val itemRepository: ItemRepository,
): ItemDatabasePort {
    override fun loadItem(itemId: Item.ItemId): Item? {
        return itemRepository.findById(itemId.value).orElse(null)?.toDomain()
    }

    override fun loadAllItems(): List<Item> {
        return itemRepository.findAll().map { item -> item.toDomain() }
    }

    override fun importItem(item: Item) {
        itemRepository.save(ItemEntity(item))
    }

    override fun importItems(items: Iterable<Item>) {
        items.forEach { item -> itemRepository.save(ItemEntity(item)) }
    }

    override fun deleteAllItems() {
        itemRepository.deleteAll()
    }
}