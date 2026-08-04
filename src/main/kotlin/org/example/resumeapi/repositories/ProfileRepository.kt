package org.example.resumeapi.repositories

import org.example.resumeapi.entities.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long>