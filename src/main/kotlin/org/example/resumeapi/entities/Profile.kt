package org.example.resumeapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.resumeapi.dtos.ProjectDto


@Entity
@Table(name = "tb_profile")
class Profile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    @Column(name = "linkedin_url")
    val linkedinUrl: String,
    @Column(name = "github_url")
    val githubUrl: String,
    @Column(name = "leetcode_url")
    val leetcodeUrl: String? = null,
    val phone: String,
    val summary: String? = null,

    @OneToMany(mappedBy = "profile")
    private val _projects: MutableSet<Project> = mutableSetOf(),
) {
    val projects get() = _projects.toList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Profile) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
    override fun toString(): String {
        return "Profile(id: $id, name: $name, email: $email, linkedinUrl: $linkedinUrl, githubUrl: $githubUrl, leetcodeUrl: $leetcodeUrl, phone: $phone, summary: $summary) )"
    }

    fun addProject(project: Project) {
        _projects.add(project)
    }

    fun removeProject(project: Project) {
        _projects.remove(project)
    }

}