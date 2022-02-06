package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.common.exceptions.TaskRunningException
import com.nimeji.gw2tp.item.application.port.`in`.RebuildItemDataUseCase
import com.nimeji.gw2tp.item.application.port.out.ItemDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicBoolean
import javax.transaction.Transactional

@Service
class RebuildItemDataService (
    @Autowired private val itemDatabasePort: ItemDatabasePort,
    @Autowired private val itemDataSourcePort: ItemDataSourcePort,
) : RebuildItemDataUseCase {
    val isRebuildTaskRunning = AtomicBoolean(false)

    @Async
    @Transactional
    override fun rebuildItemData() {
        if (isRebuildTaskRunning.get()) {
            throw TaskRunningException("rebuild task is already running")
        }

        try {
            isRebuildTaskRunning.set(true)
            itemDatabasePort.deleteAllItems()
            itemDataSourcePort.retrieveAllItems().forEach{ itemDatabasePort.importItem(it) }
        } finally {
            isRebuildTaskRunning.set(false)
        }
    }

    override fun isTaskRunning(): Boolean {
        return isRebuildTaskRunning.get()
    }
}