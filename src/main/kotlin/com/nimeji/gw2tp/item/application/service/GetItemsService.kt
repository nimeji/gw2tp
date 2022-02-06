package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.item.application.port.`in`.GetItemsUseCase
import com.nimeji.gw2tp.item.application.port.out.LoadItemPort
import com.nimeji.gw2tp.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetItemsService(
    @Autowired private val loadItemPort: LoadItemPort
) : GetItemsUseCase {
    override fun getItems(): List<Item> {
        return loadItemPort.loadAllItems()
    }
}