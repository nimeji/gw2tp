package com.nimeji.gw2tp.item.adapter.`in`.web

import com.nimeji.gw2tp.item.adapter.`in`.web.dto.ItemDto
import com.nimeji.gw2tp.item.application.port.`in`.GetItemsUseCase
import com.nimeji.gw2tp.item.application.port.`in`.UpsertItemUseCase
import com.nimeji.gw2tp.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(
    @Autowired private val getItemsUseCase: GetItemsUseCase,
    @Autowired private val upsertItemUseCase: UpsertItemUseCase,
) {
    @GetMapping
    fun getItems(): List<Item> {
        return getItemsUseCase.getItems()
    }

    @PutMapping
    fun upsertItem(@RequestBody itemDto: ItemDto) {
        upsertItemUseCase.upsertItem(itemDto.toDomain())
    }
}