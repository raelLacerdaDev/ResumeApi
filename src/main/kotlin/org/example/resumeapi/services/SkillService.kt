package org.example.resumeapi.services

import org.example.resumeapi.dtos.SkillDto
import org.example.resumeapi.entities.Skill
import org.example.resumeapi.mappers.toDto
import org.example.resumeapi.repositories.SkillRepository
import org.example.resumeapi.services.exceptions.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SkillService(
    private val skillRepository: SkillRepository
) {
    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<SkillDto> {
       return skillRepository.findAll(pageable).map { skillDto -> skillDto.toDto() }
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): SkillDto = skillRepository.findById(id).orElseThrow {
        throw ResourceNotFoundException("Resource not found")
    }.toDto()
}