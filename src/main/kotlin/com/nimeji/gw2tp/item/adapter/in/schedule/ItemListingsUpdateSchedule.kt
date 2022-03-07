package com.nimeji.gw2tp.item.adapter.`in`.schedule

import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemListingsUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ItemListingsUpdateSchedule (@Autowired val updateItemListingsUseCase: UpdateItemListingsUseCase) {
    @Scheduled(cron = "0 0 0 * * *")
    fun update() {
        updateItemListingsUseCase.updateListings()
    }

    @Scheduled(cron = "@daily")
    fun prune() {
        updateItemListingsUseCase.prune()
    }
}