package org.example.resumeapi.controllers

import org.example.resumeapi.dtos.response.ProjectDto
import org.example.resumeapi.services.ProjectService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


}