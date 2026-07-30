package org.example.resumeapi.controllers

import org.example.resumeapi.dtos.SkillDto
import org.example.resumeapi.services.SkillService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/skills")
class SkillController(
    private val service: SkillService
) {
    @GetMapping
    fun findAll(pageable: Pageable) : Page<SkillDto> {
        return service.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : SkillDto {
        return service.findById(id)
    }



}