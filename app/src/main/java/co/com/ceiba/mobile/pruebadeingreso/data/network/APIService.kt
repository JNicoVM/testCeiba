package co.com.ceiba.mobile.pruebadeingreso.data.network

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/users")
    suspend fun getUsers() : Response<List<UserResponse>>

    @GET("/post")
    suspend fun getPosts(@Query("userId") userId: Int) : Response<List<PostResponse>>
}