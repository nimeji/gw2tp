package com.nimeji.gw2tp.item.adapter.out.persistence

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ItemRepository: CrudRepository<ItemEntity, Int> {
    @Query(value = "SELECT id FROM ItemEntity")
    fun findAllIds(): Set<Int>
}