package com.jesusbadenas.kotlin_clean_architecture_project.data.api

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * APIService for retrieving data from the network using Retrofit2
 */
interface APIService {

    @GET("/android10/Sample-Data/master/Android-CleanArchitecture/users.json")
    suspend fun userDataList(): List<UserResponse>

    @GET("/android10/Sample-Data/master/Android-CleanArchitecture/user_{$USER_ID}.json")
    suspend fun userDataById(@Path(USER_ID) userId: Int): UserResponse

    companion object {
        const val API_BASE_URL = "https://raw.githubusercontent.com/"
        private const val USER_ID = "userId"
    }
}
