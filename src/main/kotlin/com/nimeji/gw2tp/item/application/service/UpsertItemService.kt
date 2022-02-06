package com.nimeji.gw2tp.item.application.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.application.port.`in`.UpsertItemUseCase
import com.nimeji.gw2tp.item.application.port.out.UpsertItemPort
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UpsertItemService(@Autowired private val upsertItemPort: UpsertItemPort) : UpsertItemUseCase {
    override fun upsertItem(item: Item): Result<Unit, ItemError> {
        upsertItemPort.upsertItem(item)

        return Ok(Unit)
    }
}