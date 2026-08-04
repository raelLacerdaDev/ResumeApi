package org.example.resumeapi.controllers

import jakarta.validation.Valid
import org.example.resumeapi.dtos.request.SkillRequestDto
import org.example.resumeapi.dtos.response.SkillDto
import org.example.resumeapi.services.SkillService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


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

    @PostMapping
    fun insert(@Valid @RequestBody skillRequestDto: SkillRequestDto): ResponseEntity<SkillDto> {
        val item = service.insert(skillRequestDto)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(item.id).toUri()
        return ResponseEntity.created(uri).body(item)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody skillRequestDto: SkillRequestDto, @PathVariable id: Long): ResponseEntity<SkillDto> {
        val dto = service.update(id, skillRequestDto)
        return ResponseEntity.ok(dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<SkillDto>{
        service.delete(id)
        return ResponseEntity.noContent().build()
    }


}