package org.example.resumeapi.repositories



import org.example.resumeapi.entities.Project
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
     fun findByProfileId(profileId: Long,pageable: Pageable) : Page<Project>
}