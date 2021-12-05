package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.data.db.DbService
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.network.HttpService
import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class AppRepository@Inject constructor(
    private val api: HttpService,
    private val dao: DbService
) {

    suspend fun getUsersFromNetwork(): Resource<List<User>> {
        return api.getUsers()
    }

    suspend fun getPostsFromNetwork(userId: Int): Resource<List<Post>> {
        return api.getPosts(userId)
    }

    suspend fun getUsersFromDb(): Resource<List<User>> {
        return dao.getUsers()
    }

    suspend fun insertUsersFromDb(users: List<User>): Resource<Boolean> {
        return dao.insertUsers(users)
    }
}

