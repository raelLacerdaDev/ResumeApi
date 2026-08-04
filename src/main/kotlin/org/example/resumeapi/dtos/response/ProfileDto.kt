package org.example.resumeapi.dtos.response



data class ProfileDto(
    val id: Long? = null,
    val name: String,
    val email: String,
    val linkedinUrl: String,
    val githubUrl: String,
    val leetcodeUrl: String? = null,
    val phone: String,
    val summary: String? = null,
    val projects: List<ProjectDto> = emptyList(),
)
