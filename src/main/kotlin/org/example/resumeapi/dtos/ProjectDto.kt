package org.example.resumeapi.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ProjectDto(
    val id: Long? = null,

    @field:NotBlank
    @field:Size(max = 255)
    val name: String,

    @field:NotBlank
    @field:Size(max = 2600)
    val description: String,

    @field:NotBlank
    @field:Size(max = 2083)
    val url: String,
)
