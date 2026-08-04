package org.example.resumeapi.services

import jakarta.persistence.EntityNotFoundException
import org.example.resumeapi.dtos.request.SkillRequestDto
import org.example.resumeapi.dtos.response.SkillDto
import org.example.resumeapi.entities.Skill
import org.example.resumeapi.mappers.toDto
import org.example.resumeapi.repositories.SkillRepository
import org.example.resumeapi.services.exceptions.DatabaseException
import org.example.resumeapi.services.exceptions.ResourceNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
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

    @Transactional
    fun insert(dto: SkillRequestDto) : SkillDto {
        val newItem = Skill(name = dto.name)
        val skill = skillRepository.save(newItem)
        return skill.toDto()
    }

    @Transactional
    fun update(id: Long, dto: SkillRequestDto): SkillDto {
        return try {
            val reference = skillRepository.getReferenceById(id)
            reference.name = dto.name
            val updated = skillRepository.save(reference)
            updated.toDto()
        } catch (_: EntityNotFoundException) {
            throw ResourceNotFoundException("Skill with $id Not Found")
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    fun delete(id: Long) {
        if (!skillRepository.existsById(id)) throw ResourceNotFoundException("Skill with $id Not Found")
        try {
            skillRepository.deleteById(id)
        } catch (_: DataIntegrityViolationException) {
            throw DatabaseException("Data integrity violation")
        }
    }

}