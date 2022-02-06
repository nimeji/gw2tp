package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.item.application.port.`in`.GetItemsUseCase
import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import com.nimeji.gw2tp.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetItemsService(
    @Autowired private val itemDatabasePort: ItemDatabasePort
) : GetItemsUseCase {
    override fun getItems(): List<Item> {
        return itemDatabasePort.loadAllItems()
    }
}