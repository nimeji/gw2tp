package com.nimeji.gw2tp.item.application.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.nimeji.gw2tp.item.application.port.`in`.AddItemListingsDatapointCommand
import com.nimeji.gw2tp.item.application.port.`in`.AddItemListingsDatapointUserCase
import com.nimeji.gw2tp.item.application.port.out.LoadItemPort
import com.nimeji.gw2tp.item.domain.ItemError
import com.nimeji.gw2tp.item.domain.Listings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddItemListingsDatapointService(
    @Autowired private val loadItemPort: LoadItemPort,
) : AddItemListingsDatapointUserCase {
    override fun addItemListingsDatapoint(command: AddItemListingsDatapointCommand): Result<Unit, ItemError> {
        return Ok(Unit)
    }
}