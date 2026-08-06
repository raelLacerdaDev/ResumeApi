package org.example.resumeapi.controllers

import org.example.resumeapi.dtos.response.ProfileDto
import org.example.resumeapi.dtos.response.ProfileSProjects
import org.example.resumeapi.dtos.response.ProfileSSkills
import org.example.resumeapi.dtos.response.SkillDto
import org.example.resumeapi.services.ProfileService
import org.example.resumeapi.services.ProjectService
import org.example.resumeapi.services.SkillService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profiles")
class ProfileController(
    private val service: ProfileService,
    private val projectService: ProjectService,
    private val skillService: SkillService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<ProfileDto>> {
        val profiles = service.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(profiles)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<ProfileDto> {
        val profile = service.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(profile)
    }

    @GetMapping("/{id}/projects")
    fun findByProjectsById(@PathVariable id: Long, pageable: Pageable) : ResponseEntity<ProfileSProjects> {
        val profile = service.findById(id)
        val projects = projectService.findByProfileId(id, pageable = pageable)
        return ResponseEntity.status(HttpStatus.OK).body(ProfileSProjects(profile = profile, projects = projects))
    }

    @GetMapping("/{id}/skills")
    fun findBySkillIdById(@PathVariable id: Long, pageable: Pageable) : ResponseEntity<ProfileSSkills> {
        val profile = service.findById(id)
        val skills = skillService.findByProfileId(id, pageable)
        return ResponseEntity.ok(ProfileSSkills(profile = profile, skills = skills))
    }



}