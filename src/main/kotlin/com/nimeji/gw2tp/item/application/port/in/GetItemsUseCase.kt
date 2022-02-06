package com.nimeji.gw2tp.item.application.port.`in`

import com.nimeji.gw2tp.item.domain.Item

interface GetItemsUseCase {
    fun getItems(): List<Item>
}