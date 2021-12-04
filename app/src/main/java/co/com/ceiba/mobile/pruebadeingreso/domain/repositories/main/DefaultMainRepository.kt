package co.com.ceiba.mobile.pruebadeingreso.domain.repositories.main

import co.com.ceiba.mobile.pruebadeingreso.data.network.APIService
import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(private val api : APIService) : MainRepository{

    /**
     * Get user from @MainRepository
     */
    override suspend fun getUsers(): Resource<UserResponse> {
        return try {
            val response = api.getUsers()
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)   // Success handler
            }else{
                Resource.Error(response.message()) // Error handler
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred" ) // Error handler
        }
    }
}