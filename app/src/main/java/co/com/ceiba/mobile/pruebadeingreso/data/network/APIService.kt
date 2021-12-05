package co.com.ceiba.mobile.pruebadeingreso.data.network

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(GET_USERS)
    suspend fun getUsers() : Response<List<UserResponse>>

    @GET(GET_POST_USER)
    suspend fun getPosts(@Query("userId") userId: Int) : Response<List<PostResponse>>
}