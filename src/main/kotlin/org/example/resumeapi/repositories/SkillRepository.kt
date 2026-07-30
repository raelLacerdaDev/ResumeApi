package org.example.resumeapi.repositories

import org.example.resumeapi.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>