package org.example.resumeapi.dtos.response

import org.springframework.data.domain.Page

data class ProfileSSkills(
    val profile: ProfileDto,
    val skills: Page<SkillDto>
)


