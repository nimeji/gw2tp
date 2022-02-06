package com.nimeji.gw2tp.item.application.port.`in`

import com.github.michaelbull.result.Result
import com.nimeji.gw2tp.item.domain.ItemError

interface AddItemListingsDatapointUserCase {
    fun addItemListingsDatapoint(command: AddItemListingsDatapointCommand): Result<Unit, ItemError>
}