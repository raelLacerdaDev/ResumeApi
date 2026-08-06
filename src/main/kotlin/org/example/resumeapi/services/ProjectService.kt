package org.example.resumeapi.services

import jakarta.persistence.EntityNotFoundException
import org.example.resumeapi.dtos.request.ProjectRequestDto
import org.example.resumeapi.dtos.response.ProjectDto
import org.example.resumeapi.entities.Project
import org.example.resumeapi.mappers.toDto
import org.example.resumeapi.repositories.ProjectRepository
import org.example.resumeapi.services.exceptions.DatabaseException
import org.example.resumeapi.services.exceptions.ResourceNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
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

    @Transactional
    fun create(dto: ProjectRequestDto) : ProjectDto {
        val newItem = Project(
            name = dto.name,
            description = dto.description,
            url = dto.url,
        )
        val saved = projectRepository.save(newItem)
        return saved.toDto()
    }


    @Transactional
    fun update(id: Long, dto: ProjectRequestDto): ProjectDto {
        if (!projectRepository.existsById(id)) throw ResourceNotFoundException("Project with id $id does not exist")
        return try {
            val reference = projectRepository.getReferenceById(id)
            reference.name = dto.name
            reference.description = dto.description
            reference.url = dto.url
            val updated = projectRepository.save(reference)
            updated.toDto()
        }catch (_: EntityNotFoundException) {
            throw ResourceNotFoundException("Project with id $id does not exist")
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    fun delete(id: Long) {
        if (!projectRepository.existsById(id)) throw ResourceNotFoundException("Project with $id Not Found")
        try {
            projectRepository.deleteById(id)
        } catch (_: DataIntegrityViolationException) {
            throw DatabaseException("Data integrity violation")
        }
    }

}