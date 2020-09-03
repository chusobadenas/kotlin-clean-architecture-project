package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserListViewModel(
    userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    val userList: LiveData<List<User>> =
        Transformations.map(userRepository.users()) { userDataList ->
            userDataMapper.mapFrom(userDataList)
        }
}