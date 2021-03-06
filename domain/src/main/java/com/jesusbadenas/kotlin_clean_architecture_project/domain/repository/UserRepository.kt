package com.jesusbadenas.kotlin_clean_architecture_project.domain.repository

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User

interface UserRepository {

    suspend fun users(): List<User>

    suspend fun user(userId: Int): User
}
