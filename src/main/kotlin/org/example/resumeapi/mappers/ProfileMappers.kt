package org.example.resumeapi.mappers

import org.example.resumeapi.dtos.response.ProfileDto
import org.example.resumeapi.entities.Profile

fun Profile.toDto() : ProfileDto = ProfileDto(
    id = this.id,
    name = this.name,
    email = this.email,
    linkedinUrl = this.linkedinUrl,
    githubUrl = this.githubUrl,
    leetcodeUrl = this.leetcodeUrl,
    phone = this.phone,
    summary = this.summary,
)