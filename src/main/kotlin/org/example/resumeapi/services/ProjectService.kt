package org.example.resumeapi.services

import org.example.resumeapi.dtos.response.ProjectDto
import org.example.resumeapi.mappers.toDto
import org.example.resumeapi.repositories.ProjectRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProjectService (
    private val projectRepository: ProjectRepository
){
    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<ProjectDto> {
        return projectRepository.findAll(pageable).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun findByProfileId(id: Long, pageable: Pageable): Page<ProjectDto> {
        val pages = projectRepository.findByProfileId(id, pageable)
        return pages.map { it.toDto() }
    }

}