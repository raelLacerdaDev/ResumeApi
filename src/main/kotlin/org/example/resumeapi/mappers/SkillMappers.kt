package org.example.resumeapi.mappers

import org.example.resumeapi.dtos.SkillDto
import org.example.resumeapi.entities.Skill


fun Skill.toDto() = SkillDto(
    id = this.id,
    name = this.name,
)