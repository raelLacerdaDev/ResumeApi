package org.example.resumeapi.dtos.response

import org.springframework.data.domain.Page


data class ProfileSProjects(
    val profile: ProfileDto,
    val projects: Page<ProjectDto>
)
