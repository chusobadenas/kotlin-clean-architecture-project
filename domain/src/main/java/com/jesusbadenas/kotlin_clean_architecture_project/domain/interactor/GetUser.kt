package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactor

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository

class GetUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Int): User = userRepository.user(userId)
}
