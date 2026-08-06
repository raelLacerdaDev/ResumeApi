package org.example.resumeapi.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_skill")
class Skill(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,

    @ManyToOne
    @JoinColumn(name="profile_id", nullable=false)
    var profile: Profile? = null,
) {
    override fun toString(): String {
        return "Skill(id=$id, name='$name')"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other !is Skill) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}