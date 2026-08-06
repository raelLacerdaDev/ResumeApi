package org.example.resumeapi.controllers

import jakarta.validation.Valid
import org.example.resumeapi.dtos.request.ProjectRequestDto
import org.example.resumeapi.dtos.response.ProjectDto
import org.example.resumeapi.services.ProjectService
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
@RequestMapping("/projects")
class ProjectController(
    private val projectService: ProjectService
) {

    // validação any role user e admin e se for user tem que ter o profile que esta associado.
    @GetMapping
    fun findAll(pageable: Pageable): Page<ProjectDto> {
        return projectService.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long, pageable: Pageable):  Page<ProjectDto> {
        return projectService.findByProfileId(id, pageable)
    }

    @PostMapping
    fun create(@Valid @RequestBody dto: ProjectRequestDto): ResponseEntity<ProjectDto> {
        val item =  projectService.create(dto)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(item.id).toUri()
        return ResponseEntity.created(uri).body(item)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: ProjectRequestDto): ResponseEntity<ProjectDto> {
        val dto = projectService.update(id, dto)
        return ResponseEntity.ok(dto)
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        projectService.delete(id)
        return ResponseEntity.noContent().build()
    }


}