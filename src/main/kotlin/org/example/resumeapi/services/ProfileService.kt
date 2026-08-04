package org.example.resumeapi.services

import org.example.resumeapi.dtos.response.ProfileDto
import org.example.resumeapi.mappers.toDto
import org.example.resumeapi.repositories.ProfileRepository
import org.example.resumeapi.services.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileService(
    private val profileRepository: ProfileRepository
) {
    @Transactional(readOnly = true)
    fun findAll() : List<ProfileDto> {
        return profileRepository.findAll().map { entity -> entity.toDto() }
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): ProfileDto {
        return profileRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Resource with id $id not found")
        }.toDto()
    }
}