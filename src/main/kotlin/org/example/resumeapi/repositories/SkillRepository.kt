package org.example.resumeapi.repositories

import org.example.resumeapi.dtos.response.SkillDto
import org.example.resumeapi.entities.Skill
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long> {

    fun findByProfileId(id: Long, pageable: Pageable): Page<Skill>
}