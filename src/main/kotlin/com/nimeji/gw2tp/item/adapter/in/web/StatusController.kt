package com.nimeji.gw2tp.item.adapter.`in`.web

import com.nimeji.gw2tp.item.adapter.`in`.web.dto.StatusDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController(
    @Autowired val environment: Environment,
    @Autowired val buildProperties: BuildProperties,
) {
    @GetMapping("status")
    fun getStatus(): StatusDto {
        return StatusDto(environment.activeProfiles, buildProperties.version)
    }
}