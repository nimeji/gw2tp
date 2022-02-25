package com.nimeji.gw2tp.item.adapter.`in`.web

import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemListingsUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//TODO: remove
@RestController
@RequestMapping("listings")
class ItemListingsController(
    @Autowired private val updateItemListingsUseCase: UpdateItemListingsUseCase,
) {
    @GetMapping
    fun updateListings() {
        updateItemListingsUseCase.updateListings()
    }
}