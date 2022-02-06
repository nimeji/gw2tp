package com.nimeji.gw2tp.item.application.port.out

import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemError

interface UpsertItemPort {
    fun upsertItem(item: Item): Result<Unit, ItemError>
}