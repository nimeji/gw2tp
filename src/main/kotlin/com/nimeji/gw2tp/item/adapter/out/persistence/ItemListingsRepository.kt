package com.nimeji.gw2tp.item.adapter.out.persistence

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface ItemListingsRepository: CrudRepository<ItemListingsEntity, Int> {
    fun deleteByTimestampIsLessThan(timestamp: Instant)
}