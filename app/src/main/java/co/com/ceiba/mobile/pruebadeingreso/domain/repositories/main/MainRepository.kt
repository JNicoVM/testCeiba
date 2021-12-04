package co.com.ceiba.mobile.pruebadeingreso.domain.repositories.main

import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource

interface MainRepository {

    /**
     * Get users
     */
    suspend fun getUsers(): Resource<List<User>>
}