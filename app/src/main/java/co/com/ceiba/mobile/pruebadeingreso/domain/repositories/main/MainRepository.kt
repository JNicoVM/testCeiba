package co.com.ceiba.mobile.pruebadeingreso.domain.repositories.main

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource

interface MainRepository {

    /**
     * Get users
     */
    suspend fun getUsers(): Resource<UserResponse>
}