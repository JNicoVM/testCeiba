package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource

interface Repository {

    suspend fun getUsersFromNetwork(): Resource<List<User>>

    suspend fun getPostsFromNetwork(userId: Int): Resource<List<Post>>

    suspend fun getUsersFromDb(): Resource<List<User>>

    suspend fun insertUsersIntoDb(users: List<User>): Resource<Boolean>
}