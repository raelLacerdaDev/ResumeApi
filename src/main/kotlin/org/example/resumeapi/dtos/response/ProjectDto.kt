package org.example.resumeapi.dtos.response



data class ProjectDto(
    val id: Long? = null,
    val name: String,
    val description: String,
    val url: String,
)
