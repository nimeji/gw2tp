package com.nimeji.gw2tp.item.application.port.`in`

import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemError

interface UpsertItemUseCase {
    fun upsertItem(item: Item): Result<Unit, ItemError>
}