package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.common.exceptions.TaskRunningException
import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemDataUseCase
import com.nimeji.gw2tp.item.application.port.out.ItemDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicBoolean
import javax.transaction.Transactional

@Service
class UpdateItemDataService (
    @Autowired private val itemDatabasePort: ItemDatabasePort,
    @Autowired private val itemDataSourcePort: ItemDataSourcePort,
) : UpdateItemDataUseCase {
    val isRebuildTaskRunning = AtomicBoolean(false)
    val logger = KotlinLogging.logger {}

    @Async
    @Transactional
    override fun importItemData() {
        if (isRebuildTaskRunning.get()) {
            throw TaskRunningException("rebuild task is already running")
        }

        try {
            logger.info { "Starting to import item data" }
            isRebuildTaskRunning.set(true)

            val localIds = itemDatabasePort.availableIds()
            val remoteIds = itemDataSourcePort.availableItemIds()

            itemDataSourcePort
                .retrieveItemsById(remoteIds.minus(localIds))
                .forEach{ itemDatabasePort.importItem(it) }
        } finally {
            logger.info { "Item data import finished" }
            isRebuildTaskRunning.set(false)
        }
    }

    override fun isTaskRunning(): Boolean {
        return isRebuildTaskRunning.get()
    }
}