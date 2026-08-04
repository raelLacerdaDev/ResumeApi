package org.example.resumeapi.dtos.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

data class ProjectRequestDto(
    val id: Long? = null,

    @field:NotBlank
    @field:Size(max = 255)
    val name: String,

    @field:NotBlank
    @field:Size(max = 2600)
    val description: String,

    @field:URL
    @field:Size(max = 2083)
    val url: String,
)
