package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import java.lang.Exception

class FakeAppRepository : Repository{

    private val userItems = mutableListOf<User>(User(email = "example@example.com", name = "Maria", username = "Maria2", id = 0, phone = "12345"))

    private val postItems = mutableListOf<Post>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetowrkError(value : Boolean){
        shouldReturnNetworkError = value
    }

    override suspend fun getUsersFromNetwork(): Resource<List<User>> {
        return if(shouldReturnNetworkError){
            Resource.Error("Error")
        }else{
            Resource.Success(userItems.toList())
        }
    }

    override suspend fun getPostsFromNetwork(userId: Int): Resource<List<Post>> {
        return if(shouldReturnNetworkError){
            Resource.Error("Error")
        }else{
            Resource.Success(listOf())
        }
    }

    override suspend fun getUsersFromDb(): Resource<List<User>> {

        return if(shouldReturnNetworkError){
            Resource.Error("Error")
        }else{
            Resource.Success(listOf())
        }
    }

    override suspend fun insertUsersIntoDb(users: List<User>): Resource<Boolean> {
       return try {
            userItems.addAll(users)
             Resource.Success(true)
        } catch (e : Exception){
             Resource.Error(e.message?:"Error")
        }

    }
}