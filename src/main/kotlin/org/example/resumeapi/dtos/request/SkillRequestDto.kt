package org.example.resumeapi.dtos.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SkillRequestDto(
    val id: Long? = null,
    @field:NotBlank
    @field:Size(max = 255)
    val name: String,
)
