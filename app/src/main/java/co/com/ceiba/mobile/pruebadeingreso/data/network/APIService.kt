package co.com.ceiba.mobile.pruebadeingreso.data.network

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("/users")
    suspend fun getUsers() : Response<List<UserResponse>>
}