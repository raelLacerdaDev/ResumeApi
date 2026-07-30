package org.example.resumeapi.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class ProfileDto(
    val id: Long? = null,

    @field:NotBlank
    @field:Size(max = 255)
    val name: String,

    @field:Email
    @field:NotBlank
    @field:Size(max = 254)
    val email: String,

    @field:NotBlank
    @field:Size(max = 2083)
    val linkedinUrl: String,

    @field:NotBlank
    @field:Size(max = 2083)
    val githubUrl: String,

    @field:Size(max = 2083)
    val leetcodeUrl: String? = null,

    @field:Size(max = 20)
    @field:NotBlank
    val phone: String,

    @field:Size(max = 2600)
    val summary: String? = null,

    val projects: List<ProjectDto> = emptyList(),
)
