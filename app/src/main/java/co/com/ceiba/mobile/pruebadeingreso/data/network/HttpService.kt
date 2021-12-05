package co.com.ceiba.mobile.pruebadeingreso.data.network

import co.com.ceiba.mobile.pruebadeingreso.data.mappers.PostNetworkDomainMapper
import co.com.ceiba.mobile.pruebadeingreso.data.mappers.UserNetworkDomainMapper
import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class HttpService  @Inject constructor(private val api: APIService){

    suspend fun getUsers(): Resource<List<User>> {
        return try {
            val response = api.getUsers()
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(UserNetworkDomainMapper().fromNetworkList(result))   // Success handler
            }else{
                Resource.Error(response.message()) // Error handler
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred" ) // Error handler
        }
    }

    suspend fun getPosts(userId: Int): Resource<List<Post>> {
        return try {
            val response = api.getPosts(userId)
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(PostNetworkDomainMapper().fromNetworkList(result))   // Success handler
            }else{
                Resource.Error(response.message()) // Error handler
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred" ) // Error handler
        }
    }

}