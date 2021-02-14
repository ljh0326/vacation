package com.hun2.server.domain.repository

import com.hun2.server.domain.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{

    fun findByEmailAndPassword(email: String, password: String): User?
    fun existsByEmailAndPassword(email: String, password: String): Boolean
}