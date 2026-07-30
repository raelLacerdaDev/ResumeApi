package org.example.resumeapi.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_project")
class Project (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String,
    val url: String,

    @ManyToOne
    @JoinColumn(name="profile_id", nullable=false)
    val profile: Profile,
) {
    override fun toString(): String {
        return "Project(id=$id, name='$name', description='$description', url='$url')"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other !is Project) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}