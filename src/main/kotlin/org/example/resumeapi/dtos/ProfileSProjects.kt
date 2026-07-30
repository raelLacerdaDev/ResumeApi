package org.example.resumeapi.dtos

import org.springframework.data.domain.Page


data class ProfileSProjects(
    val profile: ProfileDto,
    val projects: Page<ProjectDto>
)
