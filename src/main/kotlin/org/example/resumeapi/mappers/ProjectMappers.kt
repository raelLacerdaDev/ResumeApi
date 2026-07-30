package org.example.resumeapi.mappers

import org.example.resumeapi.dtos.ProjectDto
import org.example.resumeapi.entities.Project

fun Project.toDto(): ProjectDto = ProjectDto(this.id, this.name, this.description, this.url)